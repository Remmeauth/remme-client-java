package io.remme.java.keys;

import io.remme.java.enums.KeyType;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface IRemmeKeysParams {
    PublicKey getPublicKey();
    void setPublicKey(PublicKey publicKey);
    PrivateKey getPrivateKey();
    void setPrivateKey(PrivateKey privateKey);
    KeyType getKeyType();
    void setKeyType(KeyType keyType);

}
