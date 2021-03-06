package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.keys.dto.GenerateOptions;
import io.remme.java.keys.dto.KeyDTO;
import io.remme.java.utils.Functions;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.util.Asserts;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;

/**
 * RSA key type definition
 */
public class RSA extends KeyDTO implements IRemmeKeys {

    private static final Integer RSA_KEY_SIZE = 2048;

    /**
     * Constructor for RSA key pair. If only privateKey available then public key will be generate from private.
     *
     * @param privateKey private key (required)
     * @param publicKey  public key (optional)
     */
    public RSA(PublicKey publicKey, PrivateKey privateKey) {
        super();
        if (privateKey != null && publicKey != null) {
            Asserts.check(privateKey instanceof RSAPrivateKey, "Private Key should be instance of RSAPrivateKey");
            Asserts.check(publicKey instanceof RSAPublicKey, "Public Key should be instance of RSAPublicKey");
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        } else if (privateKey != null) {
            Asserts.check(privateKey instanceof RSAPrivateKey, "Private Key should be instance of RSAPrivateKey");
            this.privateKey = privateKey;
            try {
                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) this.privateKey;
                KeySpec spec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPrivateExponent());
                KeyFactory factory = KeyFactory.getInstance(KeyType.RSA.name());
                this.publicKey = factory.generatePublic(spec);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new IllegalArgumentException(e);
            }
        } else if (publicKey != null) {
            Asserts.check(publicKey instanceof RSAPublicKey, "Public Key should be instance of RSAPublicKey");
            this.publicKey = publicKey;
        }
        this.publicKeyPem = Functions.publicKeyToPem(this.publicKey);
        if (this.privateKey != null) {
            this.privateKeyPem = Functions.privateKeyToPem(this.privateKey);
        }
        this.publicKeyBase64 = Base64.encodeBase64String(publicKeyPem.getBytes(StandardCharsets.UTF_8));
        this.address = Functions.generateAddress(familyName.getName(), this.publicKey.getEncoded());
        this.keyType = KeyType.RSA.getType();
    }

    /**
     * Get address from public key
     *
     * @param publicKey public key
     * @return address in blockchain generated from public key PEM string
     */
    public static String getAddressFromPublicKey(PublicKey publicKey) {
        Asserts.check(publicKey instanceof RSAPublicKey, "Public Key should be instance of RSAPublicKey");
        return Functions.generateAddress(RemmeFamilyName.PUBLIC_KEY.getName(), publicKey.getEncoded());
    }

    private Integer calculateSaltLength(MessageDigest md) {
        int emlen = (int) Math.ceil(RSA_KEY_SIZE / 8);
        return emlen - md.getDigestLength() - 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(byte[] data, RSASignaturePadding rsaSignaturePadding) {
        try {
            if (privateKey == null) {
                throw new RemmeKeyException("PrivateKey is not provided!");
            }
            rsaSignaturePadding = rsaSignaturePadding != null ? rsaSignaturePadding : RSASignaturePadding.PSS;
            switch (rsaSignaturePadding) {
                case PSS:
                    MessageDigest hashEngine = MessageDigest.getInstance("SHA-256");
                    hashEngine.update(data);
                    // salt length
                    int saltLength = calculateSaltLength(hashEngine);
                    // create a PSSParameterSpec
                    PSSParameterSpec pssParamSpec = new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, saltLength, 1);
                    Signature pss = Signature.getInstance("SHA256withRSAandMGF1");
                    pss.setParameter(pssParamSpec);
                    pss.initSign(privateKey);
                    pss.update(data);
                    return Hex.encodeHexString(pss.sign());
                case PKCS1v15:
                    Signature pkcs1v15 = Signature.getInstance("SHA256withRSA");
                    pkcs1v15.initSign(privateKey);
                    pkcs1v15.update(data);
                    return Hex.encodeHexString(pkcs1v15.sign());
                default: {
                    throw new IllegalArgumentException("Unknown padding: " + rsaSignaturePadding.name());
                }
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(byte[] data) {
        return sign(data, RSASignaturePadding.PSS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, byte[] data, RSASignaturePadding rsaSignaturePadding) {
        try {
            byte[] signatureBytes = Hex.decodeHex(signature);
            switch (rsaSignaturePadding) {
                case PSS:
                    MessageDigest hashEngine = MessageDigest.getInstance("SHA-256");
                    hashEngine.update(data);
                    // salt length
                    int saltLength = calculateSaltLength(hashEngine);
                    // create a PSSParameterSpec
                    PSSParameterSpec pssParamSpec = new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, saltLength, 1);
                    Signature pss = Signature.getInstance("SHA256withRSAandMGF1");
                    pss.setParameter(pssParamSpec);
                    pss.initVerify(publicKey);
                    pss.update(data);
                    return pss.verify(signatureBytes);
                case PKCS1v15:
                    Signature pkcs1v15 = Signature.getInstance("SHA256withRSA");
                    pkcs1v15.initVerify(publicKey);
                    pkcs1v15.update(data);
                    return pkcs1v15.verify(signatureBytes);
                default: {
                    throw new IllegalArgumentException("Unknown padding: " + rsaSignaturePadding.name());
                }
            }
        } catch (DecoderException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, byte[] data) {
        return verify(signature, data, RSASignaturePadding.PSS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String dataString, RSASignaturePadding rsaSignaturePadding) {
       return sign(dataString.getBytes(StandardCharsets.UTF_8), rsaSignaturePadding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String data) {
        return sign(data, RSASignaturePadding.PSS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String dataString, RSASignaturePadding rsaSignaturePadding) {
        return verify(signature, dataString.getBytes(StandardCharsets.UTF_8), rsaSignaturePadding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String data) {
        return verify(signature, data, RSASignaturePadding.PSS);
    }

    /**
     * Generate public and private key pair
     *
     * @param options rsaKeySize can be specified (optional)
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(GenerateOptions options) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KeyType.RSA.name());
            generator.initialize((options != null && options.getRsaKeySize() != null) ?
                    options.getRsaKeySize() : RSA_KEY_SIZE);
            return generator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
