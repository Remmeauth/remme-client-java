package io.remme.java.keys.dto;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.error.RemmeKeyException;
import lombok.Getter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

/**
 * DTO for different kind of keys
 */
@Getter
public class KeyDTO {
    /**
     * Address of this key in blockchain. (https://docs.remme.io/remme-core/docs/family-account.html#addressing)
     */
    protected String address;
    /**
     * Private key  bytes array or rsa key type format
     */
    protected PrivateKey privateKey;
    /**
     * Public key  bytes array or rsa key type format
     */
    protected PublicKey publicKey;
    /**
     * Private key in HEX string
     */
    protected String privateKeyHex;
    /**
     * Public key in HEX string
     */
    protected String publicKeyHex;
    /**
     * Public key in PEM format
     */
    protected String publicKeyPem;
    /**
     * Private key in PEM format
     */
    protected String privateKeyPem;
    /**
     * Base64 of public key
     */
    protected String publicKeyBase64;
    /**
     * Key type
     */
    protected KeyType keyType;

    /**
     *  family name
     * @see io.remme.java.enums.RemmeFamilyName
     */
    protected RemmeFamilyName familyName = RemmeFamilyName.PUBLIC_KEY;

    //setting BouncyCastleProvider for sign/verify functions
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Return private key
     * @return String
     */
    public Object getPrivateKey() {
        if (privateKey == null) {
            throw new RemmeKeyException("You didn't provide private key");
        }
        return privateKey;
    }

    /**
     * Return private key in pem format
     * @return String
     */
    public String getPrivateKeyPem() {
        if (privateKeyPem == null) {
            throw new RemmeKeyException("Don't supported for this key type: " + keyType.name() + " or didn't provide private key");
        }
        return privateKeyPem;
    }

    /**
     * Return public key in pem format.
     * @return String
     */
    public String getPublicKeyPem() {
        if (publicKeyPem == null) {
            throw new RemmeKeyException("Don't supported for this key type: " + keyType.name());
        }
        return publicKeyPem;
    }

    /**
     * Return private key in pem format.
     * @return String
     */
    public String getPrivateKeyHex() {
        if (privateKeyHex == null) {
            throw new RemmeKeyException("Don't supported for this key type: " + keyType.name() + " or didn't provide private key");
        }
        return privateKeyHex;
    }

    /**
     * Return public key in pem format.
     * @return String
     */
    public String getPublicKeyHex() {
        if (publicKeyHex == null) {
            throw new RemmeKeyException("Don't supported for this key type: " + keyType.name());
        }
        return publicKeyHex;
    }

}
