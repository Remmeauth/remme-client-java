package io.remme.java.keys.dto;

import io.remme.java.enums.PubKeyType;
import lombok.Getter;

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
     * protected key  bytes array or rsa key type format
     */
    protected Object protectedKey;
    /**
     * Public key  bytes array or rsa key type format
     */
    protected Object publicKey;
    /**
     * protected key in HEX string
     */
    protected String protectedKeyHex;
    /**
     * Public key in HEX string
     */
    protected String publicKeyHex;
    /**
     * Public key in PEM format
     */
    protected String publicKeyPem;
    /**
     * protected key in PEM format
     */
    protected String protectedKeyPem;
    /**
     * Base64 of public key
     */
    protected String publicKeyBase64;
    /**
     * Key type
     */
    protected PubKeyType keyType;

}
