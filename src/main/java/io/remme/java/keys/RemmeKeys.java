package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.keys.dto.GenerateOptions;
import io.remme.java.utils.RemmeExecutorService;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Class that works with different types of keys.
 * For now it is RSA, EdDSA (ED25519), ECDSA (secp256k1).
 *
 */
class RemmeKeys {
    /**
     * Generate key pair of type keyType with specified options.
     *
     * <p>Example</p>
     * If you don't have key pair you can generate it.
     *<pre>
     * {@code Future<KeyPair> keys = RemmeKeys.generateKeyPair(KeyType.RSA);}
     *</pre>
     * @param keyType key type from {@link KeyType}
     * @param options options for key pair generation {@link GenerateOptions}
     * @return key pair for specified keyType
     * @see KeyPair
     */
    public static Future<KeyPair> generateKeyPair(KeyType keyType, GenerateOptions options) {
        ExecutorService es = RemmeExecutorService.getInstance();
        switch (keyType) {
            case RSA: {
                return es.submit(() -> RSA.generateKeyPair(options));
            }
            case EdDSA: {
                return es.submit(() -> EDDSA.generateKeyPair(options));
            }
            case ECDSA: {
                return es.submit(ECDSA::generateKeyPair);
            }
            default: {
                throw new RemmeKeyException("Unsupported keyType: " + keyType.name());
            }
        }
    }

    /**
     * Can get address from public key.
     *
     * If you have public key. You can get an address for it.
     * <pre>
     * String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhdw64WKrvXCWtGsNeVT\r\nPKDPpcHN0kcF4acvfPauDE8TpIFu8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZ\r\nze6YAsi7VUggO+5kDuJnKrg0VJ5swfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6Dg\r\nobdtWM/VpVYsbBcMT4XXpzmuv0qkEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y0\r\n6wr0BXqYWWQTnGC3DJf2iqu68CeoZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//K\r\nHUlnD3Oa1TyWzjF6NcMWv0PgDg6u8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7\r\nDQIDAQAB\r\n-----END PUBLIC KEY-----\r\n";
     * PublicKey publicKey = Functions.getPublicKeyFromPEM(publicKeyPEM);
     * String address = RemmeKeys.getAddressFromPublicKey(KeyType.RSA, publicKey);
     * </pre>
     * @param keyType key type from {@link KeyType}
     * @param publicKey public key for key type
     * @return address in blockchain
     */
    public static String getAddressFromPublicKey(KeyType keyType, PublicKey publicKey) {
        switch (keyType) {
            case RSA: {
                return RSA.getAddressFromPublicKey(publicKey);
            }
            case EdDSA: {
                return EDDSA.getAddressFromPublicKey(publicKey);
            }
            case ECDSA: {
                return ECDSA.getAddressFromPublicKey(publicKey);
            }
            default: {
                throw new RemmeKeyException("Unsupported keyType: " + keyType.name());
            }
        }
    }

    /**
     * Construct RemmeKeys for specific key type from scratch or based on provided private and public keys
     *
     * If you have private key. You can construct RemmeKeys based on private key.
     * <pre>
     * String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\nMIIEowIBAAKCAQEAkhdw64WKrvXCWtGsNeVTPKDPpcHN0kcF4acvfPauDE8TpIFu\r\n8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZze6YAsi7VUggO+5kDuJnKrg0VJ5s\r\nwfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6DgobdtWM/VpVYsbBcMT4XXpzmuv0qk\r\nEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y06wr0BXqYWWQTnGC3DJf2iqu68Ceo\r\nZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//KHUlnD3Oa1TyWzjF6NcMWv0PgDg6u\r\n8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7DQIDAQABAoIBADRnHCYfXMOte+2/\r\n0Bn1DIpu1I0Mm5uVxlJO+gXFJmFb7BvSIc4ENGyIDF896A+u3eNl1G5QXsBDV2Ps\r\nh9HdNKddskEtZ6ULniRhOprsMz1rnbnMqg5Y1SbrXTXVUdmB/bND53PGQ6OIX42B\r\n6vS7jFf1x89XnbcU1hJfohbUV6qvwr+hyrvrV859LM80rErCKGXXi6gtiRBiTYA3\r\n2qgO+F/ntmoU638XDzeIhKNjCP+KcWcQX1TRlrcuKfPKfCttHTb1MCGWnrOqy56w\r\nU628Iz4lKfjCOOdAXvyDRBEFSPKfriuB5JQQ67cZ9w783/2ZChhAY4wzBqvgnnlo\r\np6cPXDECgYEA+shoBswhqkA81RHxdkMoM9/iGwfkdFwxr9TqHGN7+L0hRXJlysKP\r\npBFX7Wg6GWF3BDHQzLoWQCEox0NgHbAVTC5DBxjIEjRemmlYEeAPqVRTub1lfp37\r\nYcK8BqsllDgXsqlQQNKqqVj4V2y/PO6NzlHWN9inJrp8ZZKSKPSamq8CgYEAlSF7\r\nDB0STde20E+ZPzQZi7zLWl59yM29mlKujlRktp2vl3foRJgQsndOAIc6k4+ImXR8\r\ngtfwpCYrXTQhJE4GHO/E/52vuwkVVz9qN5ZmgzR13yzlicCVmfZ7aaZ6jblNiQ1G\r\ngnIx1chcb8Vl5fncmaoa9SgefwWciPERNg31RQMCgYEApH1SjjLSWgMsY20Tfchq\r\n1Cui+Kviktft1zDGJbyzEeGrswtn7OhUov6lN5jHkuI02FF8bOwZsBKP1rNAlfhq\r\n377wQ/VjNV2YN5ulIoRegWhISmoJ6lThD6xU++LCEUgBczRO6VXEjrNGoME5ZlPq\r\nO0u+QH8gk+x5r33F1Isr5Q0CgYBHrmEjsHGU4wPnWutROuywgx3HoTWaqHHjVKy8\r\nkwoZ0O+Owb7uAZ28+qWOkXFxbgN9p0UV60+qxwH++ciYV7yOeh1ZtGS8ZSBR4JRg\r\nhbVeiX/CtyTZsqz15Ujqvm+X4aLIJo5msxcLKBRuURaqlRAY+G+euRr3eS4FkMHy\r\nFoF3GwKBgFDNeJc7VfbQq2toRSxCNuUTLXoZPrPfQrV+mA9umGCep44Ea02xIKQu\r\nbYpYghpdbASOp6fJvBlwjI8LNX3dC/PfUpbIG84bVCokgBCMNJQ+/DBuPBt7L29A\r\n7Ra1poXMbXt0nF3LgAtZHveRmVDtcr52dZ/6Yd2km5mAHj+4yPZo\r\n-----END RSA PRIVATE KEY-----\r\n"
     * IRemmeKeys keys = RemmeKeys.construct(
     *      KeyType.RSA,
     *      Functions.getPrivateKeyFromPEM(privateKey),
     * );
     * </pre>
     *
     * If you public key. You can construct RemmeKeys based on public key.
     * <pre>
     * String publicKey = "-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhdw64WKrvXCWtGsNeVT\r\nPKDPpcHN0kcF4acvfPauDE8TpIFu8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZ\r\nze6YAsi7VUggO+5kDuJnKrg0VJ5swfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6Dg\r\nobdtWM/VpVYsbBcMT4XXpzmuv0qkEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y0\r\n6wr0BXqYWWQTnGC3DJf2iqu68CeoZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//K\r\nHUlnD3Oa1TyWzjF6NcMWv0PgDg6u8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7\r\nDQIDAQAB\r\n-----END PUBLIC KEY-----\r\n";
     * IRemmeKeys keys = RemmeKeys.construct(
     *      KeyType.RSA,
     *      Functions.getPublicKeyFromPEM(publicKey),
     * );
     *</pre>
     *
     * @param keyType {@link KeyType}
     * @param privateKey private key for specified keyType
     * @param publicKey public key for specified keyType
     */
    public static IRemmeKeys construct(KeyType keyType, PublicKey publicKey, PrivateKey privateKey) {
        try {
            keyType = keyType != null ? keyType : KeyType.RSA;
            if (privateKey == null && publicKey == null) {
                KeyPair keyPair = RemmeKeys.generateKeyPair(keyType, null).get();
                privateKey = keyPair.getPrivate();
                publicKey = keyPair.getPublic();
            }

            switch (keyType) {
                case RSA: {
                    return new RSA(publicKey, privateKey);
                }
                case EdDSA: {
                    return new EDDSA(privateKey, publicKey);
                }
                case ECDSA: {
                    return new ECDSA(privateKey, publicKey);
                }
                default: {
                    throw new RemmeKeyException("Unsupported keyType: " + keyType.name());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RemmeKeyException(e);
        }
    }
}