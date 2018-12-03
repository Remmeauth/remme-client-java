package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.keys.dto.KeyDTO;
import io.remme.java.utils.Functions;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.util.Asserts;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;

/**
 * ECDSA(secp256k1) key type definition
 */
public class ECDSA extends KeyDTO implements IRemmeKeys {

    /**
     * Constructor for ECDSA key pair. If only privateKey available then public key will be generate from private.
     * @param privateKey private key (required)
     * @param publicKey public key (optional)
     */
    public ECDSA(PrivateKey privateKey, PublicKey publicKey) {
        super();
        if (privateKey != null && publicKey != null) {
            Asserts.check(privateKey instanceof BCECPrivateKey, "Private Key should be instance of BCECPrivateKey");
            Asserts.check(publicKey instanceof BCECPublicKey, "Public Key should be instance of BCECPublicKey");
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        } else if (privateKey != null) {
            Asserts.check(privateKey instanceof BCECPrivateKey, "Private Key should be instance of BCECPrivateKey");
            this.privateKey = privateKey;
            this.publicKey = derivePubKeyFromPrivKey((BCECPrivateKey) this.privateKey);
        } else if (publicKey != null) {
            Asserts.check(publicKey instanceof BCECPublicKey, "Public Key should be instance of BCECPublicKey");
            this.publicKey = publicKey;
        }

        this.publicKeyHex = Hex.encodeHexString(this.publicKey.getEncoded());
        this.privateKeyHex = Hex.encodeHexString(this.privateKey.getEncoded());

        publicKeyBase64 = Base64.encodeBase64String(publicKeyHex.getBytes(StandardCharsets.UTF_8));

        this.address = Functions.generateAddress(RemmeFamilyName.PUBLIC_KEY.getName(), this.publicKeyBase64);
        this.keyType = KeyType.ECDSA;
    }

    /**
     * Get address from public key
     * @param publicKey public key
     * @return address in blockchain generated from public key HEX string
     */
    public static String getAddressFromPublicKey(PublicKey publicKey) {
        Asserts.check(publicKey instanceof BCECPublicKey, "Public Key should be instance of BCECPublicKey");
        String publicKeyToHex = Hex.encodeHexString(publicKey.getEncoded());
        String publicKeyBase64 = Base64.encodeBase64String(publicKeyToHex.getBytes(StandardCharsets.UTF_8));
        return Functions.generateAddress(RemmeFamilyName.PUBLIC_KEY.getName(), publicKeyBase64);
    }

    /**
     * Generate public and private key pair
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDSA", "BC");
            generator.initialize(new ECGenParameterSpec("secp256k1"));
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private PrivateKey generatePrivateKey(byte[] keyBin) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");
        KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
        ECNamedCurveSpec params = new ECNamedCurveSpec("secp256k1", spec.getCurve(), spec.getG(), spec.getN());
        ECPrivateKeySpec privKeySpec = new ECPrivateKeySpec(new BigInteger(keyBin), params);
        return kf.generatePrivate(privKeySpec);
    }

    private PublicKey derivePubKeyFromPrivKey(BCECPrivateKey definingKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");

            BigInteger d = definingKey.getD();
            org.bouncycastle.jce.spec.ECParameterSpec ecSpec =
                    definingKey.getParameters();
            ECPoint Q = definingKey.getParameters().getG().multiply(d);

            org.bouncycastle.jce.spec.ECPublicKeySpec pubSpec = new
                    org.bouncycastle.jce.spec.ECPublicKeySpec(Q, ecSpec);
            return keyFactory.generatePublic(pubSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String data) {
        try {
            if (privateKey == null) {
                throw new RemmeKeyException("PrivateKey is not provided!");
            }
            Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
            signature.initSign(privateKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(signature.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | NoSuchProviderException e) {
            throw new IllegalArgumentException(e);
        }
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
    public boolean verify(String signatureHex, String data) {
        try {
            byte[] signatureBytes = Hex.decodeHex(signatureHex);
            Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
            signature.initVerify(publicKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return signature.verify(signatureBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchProviderException | SignatureException | DecoderException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String data, RSASignaturePadding padding) {
        return verify(signature, data);
    }
}
