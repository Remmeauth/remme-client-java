package io.remme.java.publickeystorage;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.IRemmeApi;
import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.keys.IRemmeKeys;
import io.remme.java.keys.RemmeKeys;
import io.remme.java.protobuf.PubKey;
import io.remme.java.protobuf.Transaction;
import io.remme.java.publickeystorage.dto.PublicKeyInfo;
import io.remme.java.publickeystorage.dto.PublicKeyCreate;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.IRemmeTransactionService;
import io.remme.java.transactionservice.dto.CreateTransactionDto;
import io.remme.java.utils.Functions;
import io.remme.java.utils.RemmeExecutorService;
import io.remme.java.utils.models.PublicKeyRequest;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static io.remme.java.utils.Functions.*;

/**
 * Class for working with public key storage.
 */
public class RemmePublicKeyStorage implements IRemmePublicKeyStorage {

    private IRemmeApi remmeApi;
    private RemmeAccount remmeAccount;
    private IRemmeTransactionService remmeTransaction;
    private RemmeFamilyName familyName = RemmeFamilyName.PUBLIC_KEY;
    @Setter
    @Getter
    private String familyVersion = "0.1";
    private String zeroAddress = StringUtils.repeat("0", 70);
    private String settingAddress = Functions.generateSettingsAddress("remme.economy_enabled");

    private byte[] generateTransactionPayload(int method, ByteString data) {
        return Transaction.TransactionPayload.newBuilder()
                .setMethod(method)
                .setData(data).build().toByteArray();
    }

    private Future<BaseTransactionResponse> createAndSendTransaction(String[] inputs, String[] outputs, byte[] payloadBytes) {
        try {
            Future<String> transaction = this.remmeTransaction.create(CreateTransactionDto.builder()
                    .familyName(this.familyName.getName())
                    .familyVersion(this.familyVersion)
                    .inputs(inputs)
                    .outputs(outputs)
                    .payloadBytes(payloadBytes).build());
            return this.remmeTransaction.send(transaction.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Future<PublicKeyInfo> getInfoByPublicKey(String address) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> {
            try {
                checkAddress(address);
                PublicKeyRequest payload = new PublicKeyRequest(address);
                PublicKeyInfo info = this.remmeApi.sendRequest(RemmeMethod.PUBLIC_KEY, payload, PublicKeyInfo.class).get();
                if (info != null) {
                    info.setAddress(generateAddress(this.familyName.getName(), address));
                    return info;
                } else {
                    throw new RemmeKeyException("This public key was not found");
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Future<String> constructAddressFromPayload(PubKey.NewPubKeyPayload payload) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> {
            checkSha(payload.getEntityHash().toStringUtf8());
            KeyType keyType = KeyType.getByType(payload.getConfigurationCase().name());
            PublicKey publicKey = null;
            switch (keyType) {
                case RSA:
                    publicKey = Functions.getPublicKeyFromBytesArray(KeyType.RSA, payload.getRsa().getKey().toByteArray());
                    break;
                case ECDSA:
                    publicKey = Functions.getPublicKeyFromBytesArray(KeyType.ECDSA, payload.getEcdsa().getKey().toByteArray());
                    break;
                case EdDSA:
                    publicKey = Functions.getPublicKeyFromBytesArray(KeyType.EdDSA, payload.getEd25519().getKey().toByteArray());
                    break;
            }
            IRemmeKeys keys = RemmeKeys.construct(keyType, publicKey, null);
            if (!keys.verify(Hex.encodeHexString(payload.getEntityHashSignature().toByteArray()),
                    payload.getEntityHash().toByteArray())) {
                throw new RemmeValidationException("Signature not valid");
            }
            return keys.getAddress();
        });
    }

    private void verifyPayloadOwner(byte[] ownerPublicKey, byte[] signatureByOwner, PubKey.NewPubKeyPayload pubKeyPayload) {
        IRemmeKeys accountKey = RemmeKeys.construct(KeyType.ECDSA, Functions.getECDSAPublicKeyFromBytes(ownerPublicKey), null);
        PubKey.NewPubKeyPayload payload = PubKey.NewPubKeyPayload.newBuilder(pubKeyPayload).build();
        if (!accountKey.verify(Hex.encodeHexString(signatureByOwner), payload.toByteArray())) {
            throw new RemmeValidationException("Owner signature not valid");
        }
    }

    /**
     * @param remmeApi         {@link IRemmeApi}
     * @param remmeAccount     {@link RemmeAccount}
     * @param remmeTransaction {@link io.remme.java.transactionservice.RemmeTransactionService}
     *                         <pre>
     *                                                                                                  RemmeApi api = new RemmeApi();
     *                                                                                                  RemmeAccount account = new RemmeAccount();
     *                                                                                                  IRemmeTransactionService transaction = new RemmeTransactionService(api, account);
     *                                                                                                  RemmePublicKeyStorage publicKeyStorage = new RemmePublicKeyStorage(api, account, transaction);
     *                                                                                                 </pre>
     */
    public RemmePublicKeyStorage(IRemmeApi remmeApi, RemmeAccount remmeAccount, IRemmeTransactionService remmeTransaction) {
        this.remmeApi = remmeApi;
        this.remmeAccount = remmeAccount;
        this.remmeTransaction = remmeTransaction;
    }

    /**
     * Create public key payload in bytes to store with another payer, privateKey and publicKey
     *
     * <pre>
     * KeyPair keys = RemmeKeys.generateKeyPair(KeyType.RSA).get();
     *
     * byte[] payloadBytes = remmePublicKeyStorage.create(PublicKeyCreate
     *      .data("store data")
     *      .keys(RemmeKeys.construct(KeyType.RSA, keys.getPublic(), keys.getPrivate()).get())
     *      .rsaSignaturePadding(RSASignaturePadding.PSS)
     *      .validFrom(Math.round(System.currentTimeMillis() / 1000))
     *      .validTo(Math.round(System.currentTimeMillis() / 1000 + 1000))
     *      .doOwnerPay(false).build());
     *</pre>
     *
     * Create public key payload in bytes to store with privateKey
     *
     * <pre>
     * KeyPair keys = RemmeKeys.generateKeyPair(KeyType.RSA).get();
     *
     * byte[] payloadBytes = remmePublicKeyStorage.create(PublicKeyCreate
     *      .data("store data")
     *      .keys(RemmeKeys.construct(KeyType.RSA, keys.getPublic(), keys.getPrivate()).get())
     *      .rsaSignaturePadding(RSASignaturePadding.PSS)
     *      .validFrom(Math.round(System.currentTimeMillis() / 1000))
     *      .validTo(Math.round(System.currentTimeMillis() / 1000 + 1000))
     *      .doOwnerPay(true).build());
     * </pre>
     *
     * Create public key payload in bytes to store with another payer with publicKey and signature
     *
     * <pre>
     * KeyPair keys = RemmeKeys.generateKeyPair(KeyType.ECDSA).get();
     * IRemmeKeys keysFromPrivate = RemmeKeys.construct(
     *      KeyType.ECDSA,
     *      keys.getPublic(),
     *      keys.getPrivate()).get();
     *
     * // Sign data with privateKey
     *
     * String data = "test";
     * String signature = keysFromPrivate.sign(DigestUtils.sha512Hex(data));
     *
     * // Construct keys from publicKey;
     *
     * IRemmeKeys keysFromPublic = Remme.Keys.construct(KeyType.ECDSA, keys.getPublic(), null).get();
     *
     * // Create public key payload with publicKey only and signature.
     * // To store keys with signature sign data should be in sha512 or sha256 format.
     *
     * byte[] payloadBytes = remmePublicKeyStorage.create(PublicKeyCreate
     *      .data(DigestUtils.sha512Hex(data))
     *      .keys(keysFromPublic)
     *      .signature(signature)
     *      .rsaSignaturePadding(RSASignaturePadding.PSS)
     *      .validFrom(Math.round(System.currentTimeMillis() / 1000))
     *      .validTo(Math.round(System.currentTimeMillis() / 1000 + 1000))
     *      .doOwnerPay(false).build());
     * </pre>
     *
     * @param data {@link PublicKeyCreate}
     * @return byte array of created payload
     */
    public byte[] create(PublicKeyCreate data) {
        try {
            if (data.getSignature() != null) {
                checkSha(data.getData());
                if (!data.getKeys().verify(data.getData(), data.getSignature())) {
                    throw new RemmeValidationException("Signature not valid");
                }
            }
            RSASignaturePadding padding = data.getRsaSignaturePadding() != null ?
                    data.getRsaSignaturePadding() : RSASignaturePadding.EMPTY;
            String message = data.getSignature() != null ? data.getData() : DigestUtils.sha512Hex(data.getData());

            byte[] entityHash = message.getBytes(StandardCharsets.UTF_8);
            byte[] entityHashSignature = Hex.decodeHex(data.getSignature() != null
                    ? data.getSignature() : data.getKeys().sign(message, padding));
            PubKey.NewPubKeyPayload.Builder payload = PubKey.NewPubKeyPayload.newBuilder()
                    .setEntityHash(ByteString.copyFrom(entityHash))
                    .setEntityHashSignature(ByteString.copyFrom(entityHashSignature))
                    .setValidFrom(data.getValidFrom())
                    .setHashingAlgorithm(PubKey.NewPubKeyPayload.HashingAlgorithm.SHA256)
                    .setValidTo(data.getValidTo());
            switch (KeyType.getByType(data.getKeys().getKeyType())) {
                case RSA:
                    payload.setRsa(PubKey.NewPubKeyPayload.RSAConfiguration.newBuilder()
                            .setKey(ByteString.copyFrom(data.getKeys().getPublicKey().getEncoded()))
                            .setPadding((padding.equals(RSASignaturePadding.EMPTY)
                                    ? RSASignaturePadding.PSS : padding).getProtoValue()).build());
                    break;
                case ECDSA:
                    payload.setEcdsa(PubKey.NewPubKeyPayload.ECDSAConfiguration.newBuilder()
                            .setKey(ByteString.copyFrom(Functions.hexToBytes(data.getKeys().getPublicKeyHex())))
                            .setEc(PubKey.NewPubKeyPayload.ECDSAConfiguration.EC.SECP256k1).build());
                    break;
                case EdDSA:
                    payload.setEd25519(PubKey.NewPubKeyPayload.Ed25519Configuration.newBuilder()
                            .setKey(ByteString.copyFrom(data.getKeys().getPublicKey().getEncoded())).build());
            }
            if (data.getDoOwnerPay()) {
                return payload.build().toByteArray();
            }
            String signatureByOwnerHex = this.remmeAccount.sign(
                    payload.build().toByteArray());

            return PubKey.NewPubKeyStoreAndPayPayload.newBuilder()
                    .setPubKeyPayload(payload.build())
                    .setOwnerPublicKey(ByteString.copyFrom(hexToBytes(this.remmeAccount.getPrivateKeyHex())))
                    .setSignatureByOwner(ByteString.copyFrom(hexToBytes(signatureByOwnerHex))).build().toByteArray();
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Store public key payload bytes with data into REMChain.
     * Send transaction to chain.
     *
     * <pre>
     * // payloadBytes is the transaction payload generated from method
     * // {@link #create(PublicKeyCreate)};
     * BaseTransactionResponse storeResponse = remmePublicKeyStorage.store(payloadBytes).get();
     *
     * storeResponse.connectToWebSocket((err, res) {@code ->} {
     *      if (err != null) {
     *          System.out.println(MAPPER.writeValueAsString(err));
     *          return;
     *      }
     *     System.out.println(MAPPER.writeValueAsString(res));
     * })
     * </pre>
     * @param data transaction payload
     */
    public Future<BaseTransactionResponse> store(byte[] data) {
        try {
            String pubKeyAddress;
            String ownerAddress = null;
            PubKey.NewPubKeyStoreAndPayPayload ownerPayload = PubKey.NewPubKeyStoreAndPayPayload.parseFrom(data);
            Object message = (ownerPayload.getPubKeyPayload().getEntityHash() == null
                    || ownerPayload.getPubKeyPayload().getEntityHash().isEmpty()) ?
                    PubKey.NewPubKeyPayload.parseFrom(data) : ownerPayload;

            if (message instanceof PubKey.NewPubKeyPayload) {
                pubKeyAddress = this.constructAddressFromPayload((PubKey.NewPubKeyPayload) message).get();
            } else if (message instanceof PubKey.NewPubKeyStoreAndPayPayload) {
                this.verifyPayloadOwner(((PubKey.NewPubKeyStoreAndPayPayload) message).getOwnerPublicKey().toByteArray(),
                        ((PubKey.NewPubKeyStoreAndPayPayload) message).getSignatureByOwner().toByteArray(),
                        ((PubKey.NewPubKeyStoreAndPayPayload) message).getPubKeyPayload());
                pubKeyAddress = this.constructAddressFromPayload(((PubKey.NewPubKeyStoreAndPayPayload) message)
                        .getPubKeyPayload()).get();
                ownerAddress = generateAddress(RemmeFamilyName.ACCOUNT.getName(), Hex.encodeHexString(
                        ((PubKey.NewPubKeyStoreAndPayPayload) message).getOwnerPublicKey().toByteArray()));
            } else {
                throw new RemmeValidationException("Invalid payload");
            }

            String[] inputsOutputs = new String[]{
                    pubKeyAddress,
                    this.zeroAddress,
                    this.settingAddress,
            };

            if (ownerAddress != null) {
                inputsOutputs = new String[]{
                        pubKeyAddress,
                        this.zeroAddress,
                        this.settingAddress,
                        ownerAddress
                };
            }

            byte[] payloadBytes = this.generateTransactionPayload(
                    ownerAddress != null ? PubKey.PubKeyMethod.Method.STORE_AND_PAY.getNumber()
                            : PubKey.PubKeyMethod.Method.STORE.getNumber(), ByteString.copyFrom(data));

            return this.createAndSendTransaction(inputsOutputs, inputsOutputs, payloadBytes);
        } catch (InterruptedException | ExecutionException | InvalidProtocolBufferException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Create public key payload bytes and store public key with its data into REMChain.
     * Send transaction to chain with private key.
     *
     * <pre>
     *
     * KeyPair keys = RemmeKeys.generateKeyPair(KeyType.RSA).get();
     *
     * BaseTransactionResponse storeResponse = remmePublicKeyStorage.createAndStore(PublicKeyCreate
     *      .data("store data")
     *      .keys(RemmeKeys.construct(KeyType.RSA, keys.getPublic(), keys.getPrivate()).get())
     *      .rsaSignaturePadding(RSASignaturePadding.PSS)
     *      .validFrom(Math.round(System.currentTimeMillis() / 1000))
     *      .validTo(Math.round(System.currentTimeMillis() / 1000 + 1000))
     *      .doOwnerPay(false).build());
     *
     * storeResponse.connectToWebSocket((err, res) {@code ->} {
     *      if (err != null) {
     *          System.out.println(MAPPER.writeValueAsString(err));
     *          return;
     *      }
     *     System.out.println(MAPPER.writeValueAsString(res));
     * })
     * </pre>
     *
     * Create public key payload bytes and store public key with its data into REMChain.
     * Send transaction to chain with signature.
     *
     * <pre>
     * KeyPair keys = RemmeKeys.generateKeyPair(KeyType.ECDSA).get();
     * IRemmeKeys keysFromPrivate = RemmeKeys.construct(
     *      KeyType.ECDSA,
     *      keys.getPublic(),
     *      keys.getPrivate()).get();
     *
     * // Sign data with privateKey
     *
     * String data = "test";
     * String signature = keysFromPrivate.sign(DigestUtils.sha512Hex(data));
     *
     * // Construct keys from publicKey;
     *
     * IRemmeKeys keysFromPublic = Remme.Keys.construct(KeyType.ECDSA, keys.getPublic(), null).get();
     *
     * // Create public key payload with publicKey only and signature.
     * // To store keys with signature sign data should be in sha512 or sha256 format.
     *
     * BaseTransactionResponse storeResponse = remmePublicKeyStorage.createAndStore(PublicKeyCreate
     *      .data(DigestUtils.sha512Hex(data))
     *      .keys(keysFromPublic)
     *      .signature(signature)
     *      .rsaSignaturePadding(RSASignaturePadding.PSS)
     *      .validFrom(Math.round(System.currentTimeMillis() / 1000))
     *      .validTo(Math.round(System.currentTimeMillis() / 1000 + 1000))
     *      .doOwnerPay(false).build());
     *
     * storeResponse.connectToWebSocket((err, res) {@code ->} {
     *      if (err != null) {
     *          System.out.println(MAPPER.writeValueAsString(err));
     *          return;
     *      }
     *     System.out.println(MAPPER.writeValueAsString(res));
     * })
     * </pre>
     *
     * @param data {@link PublicKeyCreate}
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> createAndStore(PublicKeyCreate data) {
        byte[] payloadBytes = this.create(data);
        return this.store(payloadBytes);
    }

    /**
     * Check public key on validity and revocation.
     * Take address of public key.
     *
     * @param address address in REMChain
     *                <pre>
     *                                                             Boolean isValid = publicKeyStorage.check(publicKeyAddress).get();
     *                                                             System.out.println(isValid) // true or false
     *                                                             </pre>
     * @return boolean value
     */
    public Future<Boolean> check(String address) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> this.getInfoByPublicKey(address).get().getIsValid());
    }

    /**
     * Get info about this public key.
     * Take address of public key.
     *
     * @param address address in REMChain
     *                <pre>
     *                PublicKeyInfo info = remmePublicKeyStorage.getInfo(publicKeyAddress).get();
     *                System.out.println(info.getAddress()); // PublicKeyInfo
     *                </pre>
     * @return information about public key
     */
    public Future<PublicKeyInfo> getInfo(String address) {
        return this.getInfoByPublicKey(address);
    }

    /**
     * Revoke public key by address.
     * Take address of public key.
     * Send transaction to chain.
     *
     * @param address address in REMChain
     *                <pre>
     *         BaseTransactionResponse revokeResponse = publicKeyStorage.revoke(publicKeyAddress);
     *         revokeResponse.connectToWebSocket((err, res) {@code ->} {
     *                     try {
     *                         if (err != null) {
     *                             System.out.println(MAPPER.writeValueAsString(err));
     *                             return;
     *                         }
     *                         System.out.println(MAPPER.writeValueAsString(res));
     *                         storeResponse.closeWebSocket();
     *                     } catch (JsonProcessingException e) {
     *                         throw new RuntimeException(e);
     *                     }
     *         })
     *                                                             </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> revoke(String address) {
        checkAddress(address);
        PubKey.RevokePubKeyPayload revokePayload = PubKey.RevokePubKeyPayload.newBuilder()
                .setAddress(address).build();
        byte[] payloadBytes = this.generateTransactionPayload(PubKey.PubKeyMethod.Method.REVOKE.getNumber(), revokePayload.toByteString());
        return this.createAndSendTransaction(new String[]{address}, new String[]{address}, payloadBytes);
    }

    /**
     * Take account address (which describe in {@link io.remme.java.enums.Patterns}) ADDRESS
     *
     * @param address address in REMChain
     *                <pre>
     *     String[] publicKeyAddresses = publicKeyStorage.getAccountPublicKeys(remmeAccount.getAddress());
     *     System.out.println(publicKeyAddresses); // string[]
     *     </pre>
     * @return array of addresses for user
     */
    public Future<String[]> getAccountPublicKeys(String address) {
        checkAddress(address);
        PublicKeyRequest payload = new PublicKeyRequest(address);
        return this.remmeApi.sendRequest(RemmeMethod.USER_PUBLIC_KEYS, payload, String[].class);
    }
}