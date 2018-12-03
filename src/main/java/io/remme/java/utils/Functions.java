package io.remme.java.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Functions {
    /**
     * Generate address for data with familyName
     *
     * @param familyName {@link io.remme.java.enums.RemmeFamilyName}
     * @param data data string to get address for
     * @return string address in blockchain
     */
    public static String generateAddress(String familyName, String data) {
        return DigestUtils.sha512Hex(familyName).substring(0, 6) + DigestUtils.sha512Hex(data).substring(0, 64);
    }

    /**
     * Generate address for data with familyName
     *
     * @param familyName {@link io.remme.java.enums.RemmeFamilyName}
     * @param data data bytes array to get address for
     * @return string address in blockchain
     */
    public static String generateAddress(String familyName, byte[] data) {
        return DigestUtils.sha512Hex(familyName).substring(0, 6) + DigestUtils.sha512Hex(data).substring(0, 64);
    }

    public static String generateSettingsAddress(String key) {
        List<String> keyParts = Arrays.asList(key.split(".", 4));
        List<String> addressParts = keyParts.stream().map(v -> DigestUtils.sha256Hex(v).substring(0, 16))
                .collect(Collectors.toList());
        while (4 - addressParts.size() != 0) {
            addressParts.add(DigestUtils.sha256Hex("").substring(0, 16));
        }
        return "000000" + String.join("", addressParts);
    }

    /**
     * Convert public key to PEM format
     * @param publicKey public key
     * @return PEM format string
     */
    public static String publicKeyToPem(PublicKey publicKey) {
        return "-----BEGIN PUBLIC KEY-----\n" +
                Base64.encodeBase64String(publicKey.getEncoded()) +
                "\n-----END PUBLIC KEY-----";
    }

    /**
     * Convert private key to PEM format
     * @param privateKey private key
     * @return PEM format string
     */
    public static String privateKeyToPem(PrivateKey privateKey) {
        return "-----BEGIN PRIVATE KEY-----\n" +
                Base64.encodeBase64String(privateKey.getEncoded()) +
                "\n-----END PRIVATE KEY-----";
    }
}
