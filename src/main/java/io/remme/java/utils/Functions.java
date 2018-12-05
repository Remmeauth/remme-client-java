package io.remme.java.utils;

import io.remme.java.enums.KeyType;
import io.remme.java.error.RemmeKeyException;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Functions {
    static {
        Security.addProvider(new BouncyCastleProvider());
        Security.addProvider(new EdDSASecurityProvider());
    }

    /**
     * Generate address for data with familyName
     *
     * @param familyName {@link io.remme.java.enums.RemmeFamilyName}
     * @param data       data string to get address for
     * @return string address in blockchain
     */
    public static String generateAddress(String familyName, String data) {
        return DigestUtils.sha512Hex(familyName).substring(0, 6) + DigestUtils.sha512Hex(data).substring(0, 64);
    }

    /**
     * Generate address for data with familyName
     *
     * @param familyName {@link io.remme.java.enums.RemmeFamilyName}
     * @param data       data bytes array to get address for
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
     *
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
     *
     * @param privateKey private key
     * @return PEM format string
     */
    public static String privateKeyToPem(PrivateKey privateKey) {
        return "-----BEGIN PRIVATE KEY-----\n" +
                Base64.encodeBase64String(privateKey.getEncoded()) +
                "\n-----END PRIVATE KEY-----";
    }

    /**
     * Convert public key in PEM format string to {@link PublicKey}
     *
     * @param pem PEM format public key
     * @return {@link PublicKey}
     * @throws IOException exception during String read process
     */
    public static PublicKey getPublicKeyFromPEM(String pem) throws IOException {
        SubjectPublicKeyInfo pubInfo = (SubjectPublicKeyInfo) new PEMParser(new StringReader(pem)).readObject();
        return getPublicKeyFromBytesArray(KeyType.RSA, pubInfo.getEncoded());
    }

    /**
     * Convert public key in PEM format file to {@link PublicKey}
     *
     * @param pem PEM format public key
     * @return {@link PublicKey}
     * @throws IOException exception during File read process
     */
    public static PublicKey getPublicKeyFromPEM(File pem) throws IOException {
        SubjectPublicKeyInfo pubInfo = (SubjectPublicKeyInfo) new PEMParser(new FileReader(pem)).readObject();
        return getPublicKeyFromBytesArray(KeyType.RSA, pubInfo.getEncoded());
    }

    /**
     * Convert private key in PEM format file to {@link PrivateKey}
     *
     * @param pem PEM format private key
     * @return {@link PrivateKey}
     * @throws IOException exception during File read process
     */
    public static PrivateKey getPrivateKeyFromPEM(File pem) throws IOException {
        PrivateKeyInfo privKeyInfo = (PrivateKeyInfo) new PEMParser(new FileReader(pem)).readObject();
        return getPrivateKeyFromBytesArray(KeyType.RSA, privKeyInfo.getEncoded());
    }

    /**
     * Convert private key in PEM format string to {@link PrivateKey}
     *
     * @param pem PEM format private key
     * @return {@link PrivateKey}
     * @throws IOException exception during String read process
     */
    public static PrivateKey getPrivateKeyFromPEM(String pem) throws IOException {
        PrivateKeyInfo privKeyInfo = (PrivateKeyInfo) new PEMParser(new StringReader(pem)).readObject();
        return getPrivateKeyFromBytesArray(KeyType.RSA, privKeyInfo.getEncoded());
    }


    /**
     * Convert bytes array to {@link PublicKey}
     *
     * @param keyType {@link KeyType}
     * @param encoded encoded public key
     * @return {@link PublicKey}
     */
    public static PublicKey getPublicKeyFromBytesArray(KeyType keyType, byte[] encoded) {
        try {
            KeyFactory factory;
            switch (keyType) {
                case RSA:
                    factory = KeyFactory.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
                    return factory.generatePublic(new X509EncodedKeySpec(encoded));
                case ECDSA:
                    factory = KeyFactory.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
                    return factory.generatePublic(new X509EncodedKeySpec(encoded));
                case EdDSA:
                    factory = KeyFactory.getInstance("EdDSA", EdDSASecurityProvider.PROVIDER_NAME);
                    return factory.generatePublic(new X509EncodedKeySpec(encoded));
                default: {
                    throw new RemmeKeyException("Unsupported key type!");
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            throw new RemmeKeyException(e);
        }
    }

    /**
     * Convert bytes array to {@link PrivateKey}
     *
     * @param keyType {@link KeyType}
     * @param encoded encoded private key
     * @return {@link PrivateKey}
     */
    public static PrivateKey getPrivateKeyFromBytesArray(KeyType keyType, byte[] encoded) {
        try {
            KeyFactory factory;
            switch (keyType) {
                case RSA:
                    factory = KeyFactory.getInstance("RSA", BouncyCastleProvider.PROVIDER_NAME);
                    return factory.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                case ECDSA:
                    factory = KeyFactory.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
                    return factory.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                case EdDSA:
                    factory = KeyFactory.getInstance("EdDSA", EdDSASecurityProvider.PROVIDER_NAME);
                    return factory.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                default: {
                    throw new RemmeKeyException("Unsupported key type!");
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            throw new RemmeKeyException(e);
        }
    }
}
