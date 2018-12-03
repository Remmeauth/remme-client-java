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