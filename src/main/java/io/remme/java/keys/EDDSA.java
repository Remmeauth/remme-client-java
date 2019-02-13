package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.keys.dto.GenerateOptions;
import io.remme.java.keys.dto.KeyDTO;
import io.remme.java.utils.Functions;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import net.i2p.crypto.eddsa.spec.*;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.util.Asserts;

import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * EDDSA(ed25519) key type definition
 */
public class EDDSA extends KeyDTO implements IRemmeKeys {

    /**
     * Constructor for EdDSA key pair. If only privateKey available then public key will be generate from private.
     * @param privateKey private key (required)
     * @param publicKey public key (optional)
     */
    public EDDSA(PrivateKey privateKey, PublicKey publicKey) {
        super();
        if (privateKey != null && publicKey != null) {
            Asserts.check(privateKey instanceof EdDSAPrivateKey, "Private Key should be instance of EdDSAPrivateKey");
            Asserts.check(publicKey instanceof EdDSAPublicKey, "Public Key should be instance of EdDSAPublicKey");
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        } else if (privateKey != null) {
            Asserts.check(privateKey instanceof EdDSAPrivateKey, "Private Key should be instance of EdDSAPrivateKey");
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
            this.privateKey = privateKey;
            this.publicKey = new EdDSAPublicKey(new EdDSAPublicKeySpec(((EdDSAPrivateKey)this.privateKey).getA(), spec));
        } else if (publicKey != null) {
            Asserts.check(publicKey instanceof EdDSAPublicKey, "Public Key should be instance of EdDSAPublicKey");
            this.publicKey = publicKey;
        }

        this.publicKeyHex = Hex.encodeHexString(this.publicKey.getEncoded());
        if (privateKey != null) {
            this.privateKeyHex = Hex.encodeHexString(this.privateKey.getEncoded());
        }

        publicKeyBase64 = Base64.encodeBase64String(publicKeyHex.getBytes(StandardCharsets.UTF_8));

        this.address = Functions.generateAddress(familyName.getName(), this.publicKeyHex);
        this.keyType = KeyType.EdDSA.getType();
    }

    /**
     * Get address from public key
     * @param publicKey public key
     * @return address in blockchain generated from public key HEX string
     */
    public static String getAddressFromPublicKey(PublicKey publicKey) {
        Asserts.check(publicKey instanceof EdDSAPublicKey, "Public Key should be instance of EdDSAPublicKey");
        return Functions.generateAddress(RemmeFamilyName.PUBLIC_KEY.getName(), Hex.encodeHexString(publicKey.getEncoded()));
    }

    /**
     * Generate public and private key pair
     * @param options seed bytes array can be specified (optional)
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(GenerateOptions options) {
        if (options != null && options.getSeed() != null) {
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
            EdDSAPrivateKeySpec privKey = new EdDSAPrivateKeySpec(options.getSeed(), spec);
            EdDSAPublicKeySpec pubKey = new EdDSAPublicKeySpec(privKey.getA(), spec);
            return new KeyPair(new EdDSAPublicKey(pubKey), new EdDSAPrivateKey(privKey));
        } else {
            return generateKeyPair();
        }
    }
    /**
     * Generate public and private key pair
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("EdDSA", new EdDSASecurityProvider());
            generator.initialize(new EdDSAGenParameterSpec(EdDSANamedCurveTable.ED_25519));
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(byte[] data) {
        try {
            if (privateKey == null) {
                throw new RemmeKeyException("PrivateKey is not provided!");
            }
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data);
            Signature signature = Signature.getInstance(EdDSAEngine.SIGNATURE_ALGORITHM, new EdDSASecurityProvider());
            signature.initSign(privateKey);
            signature.update(md.digest());
            return Hex.encodeHexString(signature.sign());
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String dataString) {
        return sign(dataString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String data, RSASignaturePadding padding) {
        return sign(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(byte[] data, RSASignaturePadding padding) {
        return sign(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data);
            byte[] signatureBytes = Hex.decodeHex(signature);
            Signature eddsa = Signature.getInstance(EdDSAEngine.SIGNATURE_ALGORITHM, new EdDSASecurityProvider());
            eddsa.initVerify(publicKey);
            eddsa.update(md.digest());
            return eddsa.verify(signatureBytes);
        } catch (NoSuchAlgorithmException | DecoderException | SignatureException | InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String dataString, RSASignaturePadding padding) {
        return verify(signature, dataString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String dataString) {
        return verify(signature, dataString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, byte[] data, RSASignaturePadding padding) {
        return verify(signature, data);
    }
}
