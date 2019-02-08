package io.remme.java.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Available security key types
 */
@Getter
@AllArgsConstructor
public enum KeyType {
    RSA("rsa"),
    ECDSA("ecdsa"),
    EdDSA("ed25519");

    private String type;

    public static KeyType getByType(String keyType) {
        return Stream.of(KeyType.values()).filter(val -> val.getType().equalsIgnoreCase(keyType)).findFirst().orElse(null);
    }
}
