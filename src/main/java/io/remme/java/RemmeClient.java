package io.remme.java;

import io.remme.java.account.RemmeAccount;
import io.remme.java.api.IRemmeApi;
import io.remme.java.api.RemmeApi;
import io.remme.java.atomicswap.IRemmeSwap;
import io.remme.java.atomicswap.RemmeSwap;
import io.remme.java.blockchaininfo.IRemmeBlockchainInfo;
import io.remme.java.blockchaininfo.RemmeBlockchainInfo;
import io.remme.java.certificate.IRemmeCertificate;
import io.remme.java.certificate.RemmeCertificate;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.publickeystorage.IRemmePublicKeyStorage;
import io.remme.java.publickeystorage.RemmePublicKeyStorage;
import io.remme.java.token.IRemmeToken;
import io.remme.java.token.RemmeToken;
import io.remme.java.transactionservice.IRemmeTransactionService;
import io.remme.java.transactionservice.RemmeTransactionService;
import io.remme.java.websocketevents.IRemmeWebSocketEvents;
import io.remme.java.websocketevents.RemmeWebSocketEvents;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Class representing a client for Remme.
 */
@Getter
public class RemmeClient {
    private IRemmeApi remmeApi;
    private RemmeAccount account;
    private IRemmeTransactionService transaction;

    /**
     * This properties hold implementation of RemmePublicKeyStorage,
     * which get a possibility to work with public key.
     *
     * Store public key in the network. PrivateKey is used only for generation signature.
     *
     * <pre>
     *      RemmeClient remme = new RemmeClient();
     *      String certificate = "-----BEGIN CERTIFICATE-----\r\nMIIDATCCAemgAwIBAgIHAVM4JmMVEDANBgkqhkiG9w0BAQsFADAAMB4XDTE4MDgw\r\nOTE0NTcxNFoXDTE5MDgwNDE0NTcxNFowgYExEjAQBgNVBAMTCXVzZXJOYW1lMTEd\r\nMBsGCSqGSIb3DQEJARMOdXNlckBlbWFpbC5jb20xDTALBgNVBCoTBEpvaG4xDjAM\r\nBgNVBAQTBVNtaXRoMQswCQYDVQQGEwJVUzEKMAgGAQATAzM2MDEUMBIGAQATDTE1\r\nMzM4MjY2MzE1MTAwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCSF3Dr\r\nhYqu9cJa0aw15VM8oM+lwc3SRwXhpy989q4MTxOkgW7ysVB2cZ0GV0kyj6IcLhWQ\r\nRztKo/RntvKcFdnN7pgCyLtVSCA77mQO4mcquDRUnmzB9X8l2+P16/WIbVN5VMDK\r\nnVS+OZ6T24CHoOCht21Yz9WlVixsFwxPhdenOa6/SqQR/1iZHeQnlIEZ2RvqNo6e\r\nMlY7Q7qQ5Q7njLTrCvQFephZZBOcYLcMl/aKq7rwJ6hmyD91E2zXNc/jHTQbI5bs\r\n52eYxSzJKq5//8odSWcPc5rVPJbOMXo1wxa/Q+AODq7yrjvf1fS54E0OlcnKIwyl\r\nBRn/xhtb8iN1+/sNAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAHs/oTQRl2Dhlnk6\r\ncA0ZG5DDHvh2CFVcrKaA4DEgrVmPJ3YUL+xYzrkfhT7v8a53AImcBqrZmgN8y7I1\r\n8Yj1z3EQikCwkhVNI6yRpEU7icgqNsGBXzFJ2syH3Cntt2WOqND+M4vS/yL/XY0I\r\nVNo/goW/QoGHs5082ZsfUhZs1k9+wpVOygzug9orcd1myP6ddBu7dllcDDtpB0uC\r\nh9QxMh5mGZbai2MU0lSQqSoYqwbYosCcO+Ha/RI97/lvp1R89/5K9uFmRotDljLH\r\nvty07Cw2I1cHTYcUuZJr3WAmMiMvuCdwky3V3U6xqv1Q+LRNIvE1DqqFFeaEKbkC\r\nhj2Cw9o=\r\n-----END CERTIFICATE-----\r\n"
     *      String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\nMIIEowIBAAKCAQEAkhdw64WKrvXCWtGsNeVTPKDPpcHN0kcF4acvfPauDE8TpIFu\r\n8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZze6YAsi7VUggO+5kDuJnKrg0VJ5s\r\nwfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6DgobdtWM/VpVYsbBcMT4XXpzmuv0qk\r\nEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y06wr0BXqYWWQTnGC3DJf2iqu68Ceo\r\nZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//KHUlnD3Oa1TyWzjF6NcMWv0PgDg6u\r\n8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7DQIDAQABAoIBADRnHCYfXMOte+2/\r\n0Bn1DIpu1I0Mm5uVxlJO+gXFJmFb7BvSIc4ENGyIDF896A+u3eNl1G5QXsBDV2Ps\r\nh9HdNKddskEtZ6ULniRhOprsMz1rnbnMqg5Y1SbrXTXVUdmB/bND53PGQ6OIX42B\r\n6vS7jFf1x89XnbcU1hJfohbUV6qvwr+hyrvrV859LM80rErCKGXXi6gtiRBiTYA3\r\n2qgO+F/ntmoU638XDzeIhKNjCP+KcWcQX1TRlrcuKfPKfCttHTb1MCGWnrOqy56w\r\nU628Iz4lKfjCOOdAXvyDRBEFSPKfriuB5JQQ67cZ9w783/2ZChhAY4wzBqvgnnlo\r\np6cPXDECgYEA+shoBswhqkA81RHxdkMoM9/iGwfkdFwxr9TqHGN7+L0hRXJlysKP\r\npBFX7Wg6GWF3BDHQzLoWQCEox0NgHbAVTC5DBxjIEjRemmlYEeAPqVRTub1lfp37\r\nYcK8BqsllDgXsqlQQNKqqVj4V2y/PO6NzlHWN9inJrp8ZZKSKPSamq8CgYEAlSF7\r\nDB0STde20E+ZPzQZi7zLWl59yM29mlKujlRktp2vl3foRJgQsndOAIc6k4+ImXR8\r\ngtfwpCYrXTQhJE4GHO/E/52vuwkVVz9qN5ZmgzR13yzlicCVmfZ7aaZ6jblNiQ1G\r\ngnIx1chcb8Vl5fncmaoa9SgefwWciPERNg31RQMCgYEApH1SjjLSWgMsY20Tfchq\r\n1Cui+Kviktft1zDGJbyzEeGrswtn7OhUov6lN5jHkuI02FF8bOwZsBKP1rNAlfhq\r\n377wQ/VjNV2YN5ulIoRegWhISmoJ6lThD6xU++LCEUgBczRO6VXEjrNGoME5ZlPq\r\nO0u+QH8gk+x5r33F1Isr5Q0CgYBHrmEjsHGU4wPnWutROuywgx3HoTWaqHHjVKy8\r\nkwoZ0O+Owb7uAZ28+qWOkXFxbgN9p0UV60+qxwH++ciYV7yOeh1ZtGS8ZSBR4JRg\r\nhbVeiX/CtyTZsqz15Ujqvm+X4aLIJo5msxcLKBRuURaqlRAY+G+euRr3eS4FkMHy\r\nFoF3GwKBgFDNeJc7VfbQq2toRSxCNuUTLXoZPrPfQrV+mA9umGCep44Ea02xIKQu\r\nbYpYghpdbASOp6fJvBlwjI8LNX3dC/PfUpbIG84bVCokgBCMNJQ+/DBuPBt7L29A\r\n7Ra1poXMbXt0nF3LgAtZHveRmVDtcr52dZ/6Yd2km5mAHj+4yPZo\r\n-----END RSA PRIVATE KEY-----\r\n"
     *      IRemmeKeys keys = RemmeKeys.construct(KeyType.RSA, null, Functions.privateKeyFromPem(privateKey));
     *      Integer validFrom = Math.floor(System.currentTimeMillis() / 1000);
     *      Integer validTo = Math.floor(System.currentTimeMillis() / 1000) + 365;
     *      BaseTransactionResponse store = remme.getPublicKeyStorage().store(PublicKeyStore.builder()
     *          .data(certificate)
     *          .keys(keys)
     *          .rsaSignaturePadding( NewPubKeyPayload.RSAConfiguration.Padding.PSS)
     *          .validFrom(validFrom)
     *          .validTo(validTo)
     *      .build()).get();
     *      store.connectToWebSocket((err, resp) {@code ->} {
     *          if (err != null) {
     *              throw new RuntimeException(err.getMessage());
     *          }
     *          System.out.println(new ObjectMapper().writeValueAsString(response));
     *      })
     * </pre>
     *
     * Revoke public key.
     *
     * <pre>
     *      String publicKey = "-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhdw64WKrvXCWtGsNeVT\r\nPKDPpcHN0kcF4acvfPauDE8TpIFu8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZ\r\nze6YAsi7VUggO+5kDuJnKrg0VJ5swfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6Dg\r\nobdtWM/VpVYsbBcMT4XXpzmuv0qkEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y0\r\n6wr0BXqYWWQTnGC3DJf2iqu68CeoZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//K\r\nHUlnD3Oa1TyWzjF6NcMWv0PgDg6u8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7\r\nDQIDAQAB\r\n-----END PUBLIC KEY-----\r\n";
     *      String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, Functions.publicKeyFromPem(publicKey));
     *      BaseTransactionResponse revoke = remme.getPublicKeyStorage().revoke(address).get();
     * </pre>
     *
     * Check public key.
     *
     * <pre>
     *      String publicKey = "-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhdw64WKrvXCWtGsNeVT\r\nPKDPpcHN0kcF4acvfPauDE8TpIFu8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZ\r\nze6YAsi7VUggO+5kDuJnKrg0VJ5swfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6Dg\r\nobdtWM/VpVYsbBcMT4XXpzmuv0qkEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y0\r\n6wr0BXqYWWQTnGC3DJf2iqu68CeoZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//K\r\nHUlnD3Oa1TyWzjF6NcMWv0PgDg6u8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7\r\nDQIDAQAB\r\n-----END PUBLIC KEY-----\r\n";
     *      String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, Functions.publicKeyFromPem(publicKey));
     *      boolean status = remme.getPublicKeyStorage().check(address).get();
     *      System.out.println(status);
     * </pre>
     */
    private IRemmePublicKeyStorage publicKeyStorage;

    /**
     * This properties hold implementation of RemmeCertificate,
     * which get a possibility to work with certificates.
     *
     * Store certificate's public key and hash of certificate in the network.
     *
     * <pre>
     *      RemmeClient remme = new RemmeClient();
     *      const certificateTransactionResult = remme.getCertificate().createAndStore(CreateCertificateDTO.builder()
     *          .commonName("userName1")
     *          .email("user@email.com")
     *          .name("John"),
     *          .surname("Smith")
     *          .countryName("US")
     *          .validity(360)
     *          .serial(String.valueOf(System.currentTimeMillis()))
     *      }).get();
     *      certificateTransactionResult.connectToWebSocket((err, resp) {@code ->} {
     *          if (err != null) {
     *              throw new RuntimeException(err.getMessage());
     *          }
     *          System.out.println(new ObjectMapper().writeValueAsString(response));
     *      })
     * </pre>
     *
     * Revoke certificate's public key.
     *
     * <pre>
     *      String certificate = "-----BEGIN CERTIFICATE-----\r\nMIIDATCCAemgAwIBAgIHAVM4JmMVEDANBgkqhkiG9w0BAQsFADAAMB4XDTE4MDgw\r\nOTE0NTcxNFoXDTE5MDgwNDE0NTcxNFowgYExEjAQBgNVBAMTCXVzZXJOYW1lMTEd\r\nMBsGCSqGSIb3DQEJARMOdXNlckBlbWFpbC5jb20xDTALBgNVBCoTBEpvaG4xDjAM\r\nBgNVBAQTBVNtaXRoMQswCQYDVQQGEwJVUzEKMAgGAQATAzM2MDEUMBIGAQATDTE1\r\nMzM4MjY2MzE1MTAwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCSF3Dr\r\nhYqu9cJa0aw15VM8oM+lwc3SRwXhpy989q4MTxOkgW7ysVB2cZ0GV0kyj6IcLhWQ\r\nRztKo/RntvKcFdnN7pgCyLtVSCA77mQO4mcquDRUnmzB9X8l2+P16/WIbVN5VMDK\r\nnVS+OZ6T24CHoOCht21Yz9WlVixsFwxPhdenOa6/SqQR/1iZHeQnlIEZ2RvqNo6e\r\nMlY7Q7qQ5Q7njLTrCvQFephZZBOcYLcMl/aKq7rwJ6hmyD91E2zXNc/jHTQbI5bs\r\n52eYxSzJKq5//8odSWcPc5rVPJbOMXo1wxa/Q+AODq7yrjvf1fS54E0OlcnKIwyl\r\nBRn/xhtb8iN1+/sNAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAHs/oTQRl2Dhlnk6\r\ncA0ZG5DDHvh2CFVcrKaA4DEgrVmPJ3YUL+xYzrkfhT7v8a53AImcBqrZmgN8y7I1\r\n8Yj1z3EQikCwkhVNI6yRpEU7icgqNsGBXzFJ2syH3Cntt2WOqND+M4vS/yL/XY0I\r\nVNo/goW/QoGHs5082ZsfUhZs1k9+wpVOygzug9orcd1myP6ddBu7dllcDDtpB0uC\r\nh9QxMh5mGZbai2MU0lSQqSoYqwbYosCcO+Ha/RI97/lvp1R89/5K9uFmRotDljLH\r\nvty07Cw2I1cHTYcUuZJr3WAmMiMvuCdwky3V3U6xqv1Q+LRNIvE1DqqFFeaEKbkC\r\nhj2Cw9o=\r\n-----END CERTIFICATE-----\r\n"
     *      BaseTransactionResponse revoke = remme.getCertificate().revoke(certificate).get();
     * </pre>
     *
     * Check certificate's public key.
     *
     * <pre>
     *      String certificate = "-----BEGIN CERTIFICATE-----\r\nMIIDATCCAemgAwIBAgIHAVM4JmMVEDANBgkqhkiG9w0BAQsFADAAMB4XDTE4MDgw\r\nOTE0NTcxNFoXDTE5MDgwNDE0NTcxNFowgYExEjAQBgNVBAMTCXVzZXJOYW1lMTEd\r\nMBsGCSqGSIb3DQEJARMOdXNlckBlbWFpbC5jb20xDTALBgNVBCoTBEpvaG4xDjAM\r\nBgNVBAQTBVNtaXRoMQswCQYDVQQGEwJVUzEKMAgGAQATAzM2MDEUMBIGAQATDTE1\r\nMzM4MjY2MzE1MTAwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCSF3Dr\r\nhYqu9cJa0aw15VM8oM+lwc3SRwXhpy989q4MTxOkgW7ysVB2cZ0GV0kyj6IcLhWQ\r\nRztKo/RntvKcFdnN7pgCyLtVSCA77mQO4mcquDRUnmzB9X8l2+P16/WIbVN5VMDK\r\nnVS+OZ6T24CHoOCht21Yz9WlVixsFwxPhdenOa6/SqQR/1iZHeQnlIEZ2RvqNo6e\r\nMlY7Q7qQ5Q7njLTrCvQFephZZBOcYLcMl/aKq7rwJ6hmyD91E2zXNc/jHTQbI5bs\r\n52eYxSzJKq5//8odSWcPc5rVPJbOMXo1wxa/Q+AODq7yrjvf1fS54E0OlcnKIwyl\r\nBRn/xhtb8iN1+/sNAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAHs/oTQRl2Dhlnk6\r\ncA0ZG5DDHvh2CFVcrKaA4DEgrVmPJ3YUL+xYzrkfhT7v8a53AImcBqrZmgN8y7I1\r\n8Yj1z3EQikCwkhVNI6yRpEU7icgqNsGBXzFJ2syH3Cntt2WOqND+M4vS/yL/XY0I\r\nVNo/goW/QoGHs5082ZsfUhZs1k9+wpVOygzug9orcd1myP6ddBu7dllcDDtpB0uC\r\nh9QxMh5mGZbai2MU0lSQqSoYqwbYosCcO+Ha/RI97/lvp1R89/5K9uFmRotDljLH\r\nvty07Cw2I1cHTYcUuZJr3WAmMiMvuCdwky3V3U6xqv1Q+LRNIvE1DqqFFeaEKbkC\r\nhj2Cw9o=\r\n-----END CERTIFICATE-----\r\n"
     *      boolean  status = remme.getCertificate().check(certificate);
     *      System.out.println(status);
     * </pre>
     */
    private IRemmeCertificate certificate;


    /**
     * This properties hold implementation of RemmeToken,
     * which get a possibility to work with tokens.
     *
     * Transfer tokens to another account in the network.
     *
     * <pre>
     *      RemmeClient remme = new RemmeClient();
     *      String someAccountPublicKeyInHex = "02926476095ea28904c11f22d0da20e999801a267cd3455a00570aa1153086eb13";
     *      String someAddress = Functions.generateAddress(RemmeFamilyName.ACCOUNT.getName(), someAccountPublicKeyInHex);
     *      BaseTransactionResponse transactionResult = remme.getToken().transfer(someAddress, 1000000).get();
     *      transactionResult.connectToWebSocket((err, resp) {@code ->} {
     *          if (err != null) {
     *              throw new RuntimeException(err.getMessage());
     *          }
     *          System.out.println(new ObjectMapper().writeValueAsString(response));
     *      });
     * </pre>
     *
     * Get balance for account.
     *
     * <pre>
     *      Long balance = remme.getToken().getBalance(remme.getAccount().getAddress());
     *      System.out.println(balance);
     * </pre>
     *
     */
    private IRemmeToken token;

    /**
     * This properties hold implementation of RemmeSwap,
     * which get a possibility to work with atomic swap.
     *
     * Init swap.
     *
     * <pre>
     * String swapId = "033102e41346242476b15a3a7966eb5249271025fc7fb0b37ed3fdb4bcce6815";
     * String secret = "secretkey";
     * String secretKey = DigestUtils.sha512Hex(secret); // "039eaa877ff63694f8f09c8034403f8b5165a7418812a642396d5d539f90b170";
     * String secretLock = Hash.sha3String(secretKey); // "b605112c2d7489034bbd7beab083fb65ba02af787786bb5e3d99bb26709f4f68";
     *
     * BaseTransactionResponse init = remme.getSwap().init(SwapInitDTO.builder()
     *      .receiverAddress("112007484def48e1c6b77cf784aeabcac51222e48ae14f3821697f4040247ba01558b1")
     *      .senderAddressNonLocal("0xe6ca0e7c974f06471759e9a05d18b538c5ced11e")
     *      .amount(100L)
     *      .swapId(swapId)
     *      .secretLock(secretLock)
     *      .createdAt(Math.floor(System.currentTimeMillis() / 1000))
     * .build()).get();
     * </pre>
     *
     * Get a public key with which to enсrypt sensitive data during the swap.
     *
     * <pre>
     * String publicKey = remme.getSwap().getPublicKey().get();
     * System.out.println(publicKey);
     * </pre>
     *
     * Set secret lock if it didn't set in init method
     *
     * <pre>
     * BaseTransactionResponse setSecretLock = remme.getSwap().setSecretLock(swapId, secretLock);
     * System.out.println(setSecretLock.getBatchId());
     * </pre>
     *
     * Get information about swap
     *
     * <pre>
     * SwapInfoDTO info = remme.getSwap().getInfo(swapId);
     * System.out.println(info.getState());
     * </pre>
     *
     * Approve swap with given swap id
     *
     * <pre>
     * BaseTransactionResponse approve = remme.getSwap().approve(swapId).get();
     * </pre>
     *
     * Expire swap with given swap id
     *
     * <pre>
     * BaseTransactionResponse expire = remme.getSwap().expire(swapId);
     * </pre>
     *
     * Close swap with given swap id and secret key
     *
     * <pre>
     * BaseTransactionResponse close = remme.getSwap().close(swapId, secretKey);
     * </pre>
     */
    private IRemmeSwap swap;


    /**
     * This properties hold implementation of RemmeBlockchainInfo,
     * which get a possibility to work with blockchain information (blocks, batches, states, transactions).
     * For all information about blockchain information please see this doc:
     * <a target="_top" href="https://sawtooth.hyperledger.org/docs/core/releases/latest/rest_api/endpoint_specs.html">Endpoint Specs</a>
     *
     * Get all blocks. With different params.
     *
     * <pre>
     * BlockList blocks = remme.getBlockchainInfo().getBlocks(StateQuery.builder()
     *      .limit(2)
     *      .reverse(false)
     *      .start(1) // or "0x0000000000000001" can be set too. (block_num for start)
     * .build()).get();
     * System.out.println(blocks.getHead());
     * </pre>
     *
     * Get block by id.
     *
     * <pre>
     * String blockId = "92c77c41705f2f68d5f7bc03676d0f917b0f9ef4099ee8417bd7f2470a233f3f2da5e93ee1658588f5baa0b95c81656ed187705fb72658203a7686c9749b7ad2";
     * Block block = remme.getBlockchainInfo().getBlockById(blockId).get();
     * System.out.println(block.getHead());
     * </pre>
     *
     * Get all batches. With different params.
     *
     * <pre>
     * BatchList batches = remme.getBlockchainInfo().getBatches(StateQuery.builder()
     *      .limit(2)
     *      .reverse(false)
     *      .start("92c77c41705f2f68d5f7bc03676d0f917b0f9ef4099ee8417bd7f2470a233f3f2da5e93ee1658588f5baa0b95c81656ed187705fb72658203a7686c9749b7ad2") // batch_id
     * .build()).get();
     * System.out.println(batches.getHead());
     * </pre>
     *
     * Get block by id.
     *
     * <pre>
     * String batchId = "92c77c41705f2f68d5f7bc03676d0f917b0f9ef4099ee8417bd7f2470a233f3f2da5e93ee1658588f5baa0b95c81656ed187705fb72658203a7686c9749b7ad2";
     * Batch batch = remme.getBlockchainInfo().getBatchById(batchId).get();
     * System.out.println(batch.getHead());
     * </pre>
     *
     * Get all transactions. With different params.
     *
     * <pre>
     * TransactionList transactions = remme.getBlockchainInfo().getTransactions(StateQuery.builder()
     *      .limit(2)
     *      .reverse(false)
     *      .start("92c77c41705f2f68d5f7bc03676d0f917b0f9ef4099ee8417bd7f2470a233f3f2da5e93ee1658588f5baa0b95c81656ed187705fb72658203a7686c9749b7ad2") // batch_id
     * .build()).get();
     * System.out.println(transactions.getHead());
     * </pre>
     *
     * Get transaction by id.
     *
     * <pre>
     * String transactionId = "92c77c41705f2f68d5f7bc03676d0f917b0f9ef4099ee8417bd7f2470a233f3f2da5e93ee1658588f5baa0b95c81656ed187705fb72658203a7686c9749b7ad2";
     * Transaction transaction = remme.getBlockchainInfo().getTransactionById(transactionId).get();
     * System.out.println(transaction.getHead());
     * </pre>
     *
     * Get all state. With different params.
     *
     * <pre>
     * StateList state = remme.getBlockchainInfo().getState(StateQuery.builder().limit(2).reverse(false).build()).get();
     * System.out.println(state.getHead());
     * </pre>
     *
     * Get state by address.
     *
     * <pre>
     * String address = "112007e4e7d40588cc13f1abee0d3cf70b74a0b47bb31204c467c114f68a7f417e2f86";
     * State state = remme.getBlockchainInfo().getStateByAddress(address).get();
     * System.out.println(state.getAddress());
     * </pre>
     */
    private IRemmeBlockchainInfo blockchainInfo;

    /**
     * This properties hold implementation of RemmeWebSocketEvents,
     * which get a possibility to listen events from validator about transactions.
     *
     * Subscribe to event
     * <pre>
     * remme.getEvents().subscribe(
     *     RemmeRequestParams.builder().event_type(RemmeEvents.ATOMIC_SWAP).build(),
     *     // lastKnownBlockId <-- also can be set if you know it.
     *  (err, res) {@code ->} {
     *     System.out.println(MAPPER.writeValueAsString(res));});
     * </pre>
     *
     * Unsubscribe
     * <pre>
     * remme.getEvents().unsubscribe();
     * </pre>
     */

    public IRemmeWebSocketEvents getEvents() {
        return new RemmeWebSocketEvents(this.remmeApi.getNetworkConfig());
    }

    /**
     * @param clientInit {@link ClientInit}
     *
     * Create a client. With all configuration.
     *
     * <pre>
     * ClientInit init = new ClientInit("localhost", 8080, false, privateKeyHex)
     * RemmeClient remme = new RemmeClient(init);
     * </pre>
     *
     * But you also can initialize Client only with one networkConfig parameter.
     * In this case account would be creating from newly creating private key.
     *
     * <pre>
     * RemmeClient remme = new RemmeClient(ClientInit.builder().networkConfig(new NetworkConfig("localhost:8080", false)).build());
     * </pre>
     *
     * Also you can set only a privateKeyHex parameter. So networkConfig would be this: {
     *      nodeAddress: "localhost:8080",
     *      sslMode: false
     * }.
     *
     * <pre>
     * RemmeClient remme = new RemmeClient(ClientInit.builder().privateKeyHex(privateKeyHex).build());
     * </pre>
     *
     * Or initialize client without any parameters
     *
     * <pre>
     * RemmeClient remme = new RemmeClient();
     * </pre>
     */
    public RemmeClient(ClientInit clientInit) {
        if (clientInit.getNetworkConfig() != null) {
            this.remmeApi = new RemmeApi(clientInit.getNetworkConfig());
        } else {
            this.remmeApi = new RemmeApi();
        }
        if (StringUtils.isNotEmpty(clientInit.getPrivateKeyHex())) {
            this.account = new RemmeAccount(clientInit.getPrivateKeyHex());
        } else {
            this.account = new RemmeAccount();
        }
        this.transaction = new RemmeTransactionService(this.remmeApi, this.account);
        this.publicKeyStorage = new RemmePublicKeyStorage(this.remmeApi, this.account, this.transaction);
        this.certificate = new RemmeCertificate(this.publicKeyStorage);
        this.token = new RemmeToken(this.remmeApi, this.transaction);
        this.swap = new RemmeSwap(this.remmeApi, this.transaction);
        this.blockchainInfo = new RemmeBlockchainInfo(this.remmeApi);
    }

    /**
     * Constructor without parameters calls {@link #RemmeClient(ClientInit clientInit)} with empty ClientInit object
     */
    public RemmeClient() {
        this(new ClientInit());
    }

    public void setAccount(RemmeAccount remmeAccount) {
        if (remmeAccount == null) {
            throw new RemmeValidationException("Account is missing in attributes. Please give the account.");
        }
        if (StringUtils.isEmpty(remmeAccount.getPrivateKeyHex()) || StringUtils.isEmpty(remmeAccount.getPublicKeyHex())) {
            throw new RemmeValidationException("Given remmeAccount is not valid");
        }
        this.account = remmeAccount;
    }

    /**
     * Get information about current account
     *
     * <pre>
     * System.out.println(remme.getAccount().getAddress());
     * </pre>
     *
     * Provide an account which sign the transactions that send to our nodes.
     * For account use ECDSA (secp256k1) key pair
     *
     * <pre>
     * RemmeAccount account = RemmeClient.generateAccount();
     * remme.setAccount(account);
     * </pre>
     */
    public RemmeAccount getAccount() {
        return this.account;
    }


    /**
     * Generate a new account
     *
     * <pre>
     * RemmeClient account = RemmeClient.generateAccount();
     * System.out.println(account);
     * </pre>
     */
    public static RemmeAccount generateAccount() {
        return new RemmeAccount();
    }
}