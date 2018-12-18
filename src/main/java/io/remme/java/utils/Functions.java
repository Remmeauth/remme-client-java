package io.remme.java.utils;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.Patterns;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.error.RemmeValidationException;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openssl.PEMParser;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
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
        List<String> keyParts = Arrays.asList(key.split("\\.", 4));
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
                    return getECDSAPublicKeyFromBytes(encoded);
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
                    return generateECDSAPrivateKey(encoded);
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

    /**
     * Generates {@link BCECPrivateKey} from byte array
     *
     * @param keyBin private key byte array
     * @return {@link PrivateKey}
     */
    public static PrivateKey generateECDSAPrivateKey(byte[] keyBin) {
        try {
            ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");
            KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
            ECNamedCurveSpec params = new ECNamedCurveSpec("secp256k1", spec.getCurve(), spec.getG(), spec.getN());
            ECPrivateKeySpec privKeySpec = new ECPrivateKeySpec(new BigInteger(keyBin), params);
            return kf.generatePrivate(privKeySpec);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            throw new RemmeKeyException(e);
        }
    }

    /**
     * Generates {@link org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey} from HEX string
     *
     * @param pubHex public key HEX string
     * @return {@link PublicKey}
     */
    public static PublicKey getECDSAPublicKeyFromHex(String pubHex)  {
        try {
            String hexX = pubHex.substring(0, pubHex.length() / 2);
            String hexY = pubHex.substring(pubHex.length() / 2);
            java.security.spec.ECPoint point = new java.security.spec.ECPoint(new BigInteger(hexX, 16), new BigInteger(hexY, 16));
            ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");
            KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
            ECNamedCurveSpec params = new ECNamedCurveSpec("secp256k1", spec.getCurve(), spec.getG(), spec.getN());
            java.security.spec.ECPublicKeySpec pubKeySpec = new java.security.spec.ECPublicKeySpec(point, params);
            return kf.generatePublic(pubKeySpec);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            throw new RemmeKeyException(e);
        }
    }

    /**
     * Converts ECDSA {@link BCECPrivateKey} to HEX string
     *
     * @param privateKey EDSA private key
     * @return HEX string
     */
    public static String ecdsaPrivateKeyToHex(PrivateKey privateKey) {
        return ((BCECPrivateKey) privateKey).getS().toString(16);
    }

    /**
     * Converts ECDSA {@link BCECPublicKey} to HEX string
     *
     * @param publicKey ECDSA public key
     * @param compressed should use compression or not
     * @return HEX string
     */
    public static String ecdsaPublicKeyToHex(PublicKey publicKey, boolean compressed) {
        byte[] w = ((BCECPublicKey) publicKey).getQ().getEncoded(compressed);
        return Hex.encodeHexString(w);
    }

    /**
     * Converts HEX string to byte array
     * @param hex HEX string
     * @return bytes array
     */
    public static byte[] hexToBytes(String hex) {
        return new BigInteger(hex, 16).toByteArray();
    }

    /**
     * Compress ECDSA public key to HEX string using only X coordinate and starting with 02(even Y) or 03(odd Y)
     *
     * @param publicKey ECDSA public key {@link BCECPublicKey}
     * @return HEX string
     */
    public static String compress(PublicKey publicKey) {
        ECPoint point = ((BCECPublicKey) publicKey).getQ();
        byte[] x = point.getAffineXCoord().toBigInteger().toByteArray();
        byte[] y = point.getAffineYCoord().toBigInteger().toByteArray();
        byte[] xy = new byte[x.length + y.length];
        System.arraycopy(x, 0, xy, 0, x.length);
        System.arraycopy(y, 0, xy, x.length, y.length);
        BigInteger pubKey = new BigInteger(xy);
        return compress(pubKey);
    }

    /**
     * Compress BigInteger public key to HEX string using only X coordinate and starting with 02(even Y) or 03(odd Y)
     *
     * @param pubKey BigInteger public key {@link BigInteger}
     * @return HEX string
     */
    public static String compress(BigInteger pubKey) {
        String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
        String pubKeyHex = pubKey.toString(16);
        System.out.println(pubKeyHex.length());
        String pubKeyX = pubKeyHex.substring(0, 64);
        return pubKeyYPrefix + pubKeyX;
    }

    /**
     * Decompress public key byte array for ECDSA
     *
     * @param compressed compressed publicKey
     * @return decompressed publicKey byte array
     */
    public static byte[] uncompressPoint(byte[] compressed) {
        return SECNamedCurves.getByName("secp256k1").getCurve().decodePoint(compressed).getEncoded(false);
    }

    /**
     * Generates {@link BCECPublicKey} from ECDSA public key bytes array (both compressed and not compressed keys
     * are valid for this method)
     *
     * @param bytes public key bytes array
     * @return {@link PublicKey}
     */
    public static PublicKey getECDSAPublicKeyFromBytes(byte[] bytes) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
            ECParameterSpec ecParameterSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
            ECNamedCurveSpec params = new ECNamedCurveSpec("secp256k1", ecParameterSpec.getCurve(), ecParameterSpec.getG(), ecParameterSpec.getN());

            java.security.spec.ECPoint publicPoint = ECPointUtil.decodePoint(params.getCurve(), bytes);
            ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(publicPoint, params);
            return keyFactory.generatePublic(pubKeySpec);
        } catch (Exception e) {
             throw new RemmeKeyException(e);
        }
    }

    public static void checkAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new RemmeValidationException("Address was not provided, please set the address");
        }
        if (!address.matches(Patterns.ADDRESS.getPattern())) {
            throw new RemmeValidationException("Given address is not a valid");
        }
    }

    public static void checkPublicKey(String publicKey) {
        if (publicKey == null || publicKey.isEmpty()) {
            throw new RemmeValidationException("Public Key was not provided, please set the address");
        }
        if (!publicKey.matches(Patterns.PUBLIC_KEY.getPattern())) {
            throw new RemmeValidationException("Given public key is not a valid");
        }
    }
}
