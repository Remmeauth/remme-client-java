package io.remme.java.publickeystorage;

import com.google.protobuf.ByteString;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.IRemmeApi;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.protobuf.PubKey;
import io.remme.java.protobuf.Transaction;
import io.remme.java.publickeystorage.dto.PublicKeyInfo;
import io.remme.java.publickeystorage.dto.PublicKeyStore;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.IRemmeTransactionService;
import io.remme.java.transactionservice.dto.CreateTransactionDto;
import io.remme.java.utils.Functions;
import io.remme.java.utils.RemmeExecutorService;
import io.remme.java.utils.models.NodeConfigRequest;
import io.remme.java.utils.models.PublicKeyRequest;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static io.remme.java.utils.Functions.checkAddress;
import static io.remme.java.utils.Functions.generateAddress;
import static io.remme.java.utils.Functions.generateSettingsAddress;

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

    private String generateMessage(String data) {
        return DigestUtils.sha512Hex(data);
    }

    private String generateEntityHash(String message) {
        return Hex.encodeHexString(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param remmeApi         {@link IRemmeApi}
     * @param remmeAccount     {@link RemmeAccount}
     * @param remmeTransaction {@link io.remme.java.transactionservice.RemmeTransactionService}
     *                         <pre>
     *                                                                          RemmeApi api = new RemmeApi();
     *                                                                          RemmeAccount account = new RemmeAccount();
     *                                                                          IRemmeTransactionService transaction = new RemmeTransactionService(api, account);
     *                                                                          RemmePublicKeyStorage publicKeyStorage = new RemmePublicKeyStorage(api, account, transaction);
     *                                                                         </pre>
     */
    RemmePublicKeyStorage(IRemmeApi remmeApi, RemmeAccount remmeAccount, IRemmeTransactionService remmeTransaction) {
        this.remmeApi = remmeApi;
        this.remmeAccount = remmeAccount;
        this.remmeTransaction = remmeTransaction;
    }

    /**
     * Store public key with its data into REMChain.
     * Send transaction to chain.
     *
     * @param keyStore {@link PublicKeyStore}
     *                 <pre>
     *                                                 IRemmeKeys keys = RemmeKeys.construct(KeyType.RSA, null, null);
     *                                                 BaseTransactionResponse storeResponse = publicKeyStorage.store(PublicKeyStore.builder()
     *                                                                 .data("store data")
     *                                                                 .rsaSignaturePadding(PubKey.NewPubKeyPayload.RSAConfiguration.Padding.PSS)
     *                                                                 .keys(keys)
     *                                                                 .validFrom(validFrom)
     *                                                                 .validTo(validTo).build());
     *                 <p>
     *                                                 storeResponse.connectToWebSocket((err, res) -> {
     *                                                             try {
     *                                                                 if (err != null) {
     *                                                                     System.out.println(MAPPER.writeValueAsString(err));
     *                                                                     return;
     *                                                                 }
     *                                                                 System.out.println(MAPPER.writeValueAsString(res));
     *                                                                 storeResponse.closeWebSocket();
     *                                                             } catch (JsonProcessingException e) {
     *                                                                 throw new RuntimeException(e);
     *                                                             }
     *                                                 })
     *                                                 </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> store(PublicKeyStore keyStore) {
        try {
            PubKey.NewPubKeyPayload.RSAConfiguration.Padding padding = keyStore.getRsaSignaturePadding() != null ?
                    keyStore.getRsaSignaturePadding() : PubKey.NewPubKeyPayload.RSAConfiguration.Padding.UNRECOGNIZED;
            String message = this.generateMessage(keyStore.getData());
            String entityHash = this.generateEntityHash(message);
            String entityHashSignature = keyStore.getKeys().sign(message, padding);
            PubKey.NewPubKeyPayload.Builder payload = PubKey.NewPubKeyPayload.newBuilder()
                    .setEntityHash(ByteString.copyFrom(entityHash.getBytes(StandardCharsets.UTF_8)))
                    .setEntityHashSignature(ByteString.copyFrom(entityHashSignature.getBytes(StandardCharsets.UTF_8)))
                    .setValidFrom(keyStore.getValidFrom())
                    .setValidTo(keyStore.getValidTo());
            switch (keyStore.getKeys().getKeyType()) {
                case RSA:
                    payload.setRsa(PubKey.NewPubKeyPayload.RSAConfiguration.newBuilder()
                            .setKey(ByteString.copyFrom(keyStore.getKeys().getPublicKey().getEncoded()))
                            .setPadding(padding.equals(PubKey.NewPubKeyPayload.RSAConfiguration.Padding.UNRECOGNIZED)
                                    ? PubKey.NewPubKeyPayload.RSAConfiguration.Padding.PSS : padding).build());
                    break;
                case ECDSA:
                    payload.setEcdsa(PubKey.NewPubKeyPayload.ECDSAConfiguration.newBuilder()
                            .setKey(ByteString.copyFrom(Functions.hexToBytes(keyStore.getKeys().getPublicKeyHex())))
                            .setEc(PubKey.NewPubKeyPayload.ECDSAConfiguration.EC.SECP256k1).build());
                    break;
                case EdDSA:
                    payload.setEd25519(PubKey.NewPubKeyPayload.Ed25519Configuration.newBuilder()
                            .setKey(ByteString.copyFrom(keyStore.getKeys().getPublicKey().getEncoded())).build());
            }
            NodeConfigRequest nodeConfig = this.remmeApi.sendRequest(RemmeMethod.NODE_CONFIG, NodeConfigRequest.class).get();

            String pubKeyAddress = keyStore.getKeys().getAddress();
            String storagePublicKeyAddress = generateSettingsAddress("remme.settings.storage_pub_key");
            String settingAddress = generateSettingsAddress("remme.economy_enabled");
            String storageAddress = generateAddress(this.remmeAccount.getFamilyName().getName(), nodeConfig.getStorage_public_key());
            byte[] payloadBytes = this.generateTransactionPayload(PubKey.PubKeyMethod.Method.STORE.getNumber(), payload.build().toByteString());
            String[] inputs = new String[]{pubKeyAddress, storagePublicKeyAddress, settingAddress, storageAddress};
            String[] outputs = new String[]{pubKeyAddress, storageAddress};
            return this.createAndSendTransaction(inputs, outputs, payloadBytes);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check public key on validity and revocation.
     * Take address of public key.
     *
     * @param address address in REMChain
     *                <pre>
     *                                              Boolean isValid = publicKeyStorage.check(publicKeyAddress).get();
     *                                              System.out.println(isValid) // true or false
     *                                              </pre>
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
     *                                              const info = await remme.publicKeyStorage.getInfo(publicKeyAddress);
     *                                              console.log(info); // PublicKeyInfo
     *                                              </pre>
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
     *                                              BaseTransactionResponse revokeResponse = publicKeyStorage.revoke(publicKeyAddress);
     *                                              revokeResponse.connectToWebSocket((err, res) => {
     *                                                          try {
     *                                                              if (err != null) {
     *                                                                  System.out.println(MAPPER.writeValueAsString(err));
     *                                                                  return;
     *                                                              }
     *                                                              System.out.println(MAPPER.writeValueAsString(res));
     *                                                              storeResponse.closeWebSocket();
     *                                                          } catch (JsonProcessingException e) {
     *                                                              throw new RuntimeException(e);
     *                                                          }
     *                                              })
     *                                              </pre>
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
     * Take account address (which describe in {@link io.remme.java.enums.Patterns) ADDRESS
     *
     * @param address address in REMChain
     *                <pre>
     *                                              String[] publicKeyAddresses = publicKeyStorage.getAccountPublicKeys(remmeAccount.getAddress());
     *                                              System.out.println(publicKeyAddresses); // string[]
     *                                              </pre>
     * @returns array of addresses for user
     */
    public Future<String[]> getAccountPublicKeys(String address) {
        checkAddress(address);
        PublicKeyRequest payload = new PublicKeyRequest(address);
        return this.remmeApi.sendRequest(RemmeMethod.USER_PUBLIC_KEYS, payload, String[].class);
    }
}