package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.error.RemmeKeyException;
import io.remme.java.keys.dto.KeyDTO;
import io.remme.java.protobuf.PubKey;
import io.remme.java.utils.Functions;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.util.Asserts;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;
import java.util.Arrays;

/**
 * ECDSA(secp256k1) key type definition
 */
public class ECDSA extends KeyDTO implements IRemmeKeys {

    /**
     * Constructor for ECDSA key pair. If only privateKey available then public key will be generate from private.
     *
     * @param privateKey private key (required)
     * @param publicKey  public key (optional)
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

        this.publicKeyHex = Functions.ecdsaPublicKeyToHex(this.publicKey, true);
        if (privateKey != null) {
            this.privateKeyHex = Functions.ecdsaPrivateKeyToHex(this.privateKey);
        }

        publicKeyBase64 = Base64.encodeBase64String(publicKeyHex.getBytes(StandardCharsets.UTF_8));

        this.address = Functions.generateAddress(familyName.getName(), this.publicKeyHex);
        this.keyType = KeyType.ECDSA.getType();
    }

    /**
     * Get address from public key
     *
     * @param publicKey public key
     * @return address in blockchain generated from public key HEX string
     */
    public static String getAddressFromPublicKey(PublicKey publicKey) {
        Asserts.check(publicKey instanceof BCECPublicKey, "Public Key should be instance of BCECPublicKey");
        return Functions.generateAddress(RemmeFamilyName.PUBLIC_KEY.getName(), Functions.ecdsaPublicKeyToHex(publicKey, true));
    }

    /**
     * Generate public and private key pair
     *
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair() {
        byte[] privKey = createNewPrivateKey();
        while (privKey.length != 32) {
            privKey = createNewPrivateKey();
        }
        byte[] pubKey = getPublicFor(privKey);
        PrivateKey privateKey = Functions.generateECDSAPrivateKey(privKey);
        PublicKey publicKey = Functions.getECDSAPublicKeyFromBytes(pubKey);
        System.out.println("HEX pub:" + Hex.encodeHexString(pubKey));
        System.out.println("HEX priv:" + Hex.encodeHexString(privKey));
        return new KeyPair(publicKey, privateKey);
    }

    private static byte[] getPublicFor(byte[] privateKey) {
        return curve.getG().multiply(new BigInteger(privateKey)).getEncoded(true);
    }

    private static byte[] createNewPrivateKey() {
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keygenParams = new ECKeyGenerationParameters(domain, new SecureRandom());
        generator.init(keygenParams);
        AsymmetricCipherKeyPair keypair = generator.generateKeyPair();
        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) keypair.getPrivate();
        return privParams.getD().toByteArray();
    }

    private static PublicKey derivePubKeyFromPrivKey(BCECPrivateKey definingKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");

            BigInteger d = definingKey.getD();
            ECParameterSpec ecSpec = definingKey.getParameters();
            ECPoint Q = definingKey.getParameters().getG().multiply(d);
            ECPublicKeySpec pubSpec = new ECPublicKeySpec(Q, ecSpec);
            return keyFactory.generatePublic(pubSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchProviderException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String dataString) {
        if (privateKey == null) {
            throw new RemmeKeyException("PrivateKey is not provided!");
        }
        byte[] dataHash = DigestUtils.sha256(dataString.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(sign(dataHash, Functions.hexToBytes(privateKeyHex)));
    }

    /**
     * Makes same as {@link #sign(String)} but data in byte array format
     *
     * @param data byte array of data
     * @return signature HEX string
     */
    public String sign(byte[] data) {
        if (privateKey == null) {
            throw new RemmeKeyException("PrivateKey is not provided!");
        }
        byte[] dataHash = DigestUtils.sha256(data);
        return Hex.encodeHexString(sign(dataHash, Functions.hexToBytes(privateKeyHex)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sign(String data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding padding) {
        return sign(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signatureHex, String data) {
        try {
            byte[] tokenSignature = Hex.decodeHex(signatureHex);
            byte[] dataHash = DigestUtils.sha256(data.getBytes(StandardCharsets.UTF_8));
            return verify(dataHash, tokenSignature, Hex.decodeHex(publicKeyHex));
        } catch (DecoderException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding padding) {
        return verify(signature, data);
    }

    private byte[] sign(byte[] hash, byte[] privateKey) {
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        signer.init(true, new ECPrivateKeyParameters(new BigInteger(privateKey), domain));
        BigInteger[] signature = signer.generateSignature(hash);
        byte[] r1 = signature[0].toByteArray();
        byte[] s1 = toCanonicalS(signature[1]).toByteArray();
        byte[] r = new byte[32];
        byte[] s = new byte[32];

        if (r1.length > 32) {
            System.arraycopy(r1, 1, r, 0, r.length);
        } else if (r1.length < 32) {
            System.arraycopy(r1, 0, r, 1, r1.length);
        } else {
            r = r1;
        }
        if (s1.length > 32) {
            System.arraycopy(s1, 1, s, 0, s.length);
        } else if (s1.length < 32) {
            System.arraycopy(s1, 0, s, 1, s1.length);
        } else {
            s = s1;
        }
        byte[] rs = new byte[r.length + s.length];
        System.out.println(r.length);
        System.out.println(s.length);

        System.arraycopy(r, 0, rs, 0, r.length);
        System.arraycopy(s, 0, rs, r.length, s.length);
        return rs;
    }

    private BigInteger toCanonicalS(BigInteger s) {
        if (s.compareTo(HALF_CURVE_ORDER) <= 0) {
            return s;
        } else {
            return curve.getN().subtract(s);
        }
    }

    private boolean verify(byte[] hash, byte[] signature, byte[] publicKey) {
        ECDSASigner signer = new ECDSASigner();

        signer.init(false, new ECPublicKeyParameters(curve.getCurve().decodePoint(publicKey), domain));

        BigInteger r = new BigInteger(1, Arrays.copyOfRange(signature, 0, 32));
        BigInteger s = new BigInteger(1, Arrays.copyOfRange(signature, 32, 64));
        if (!signer.verifySignature(hash, r, s)) {
            System.out.println("something");
        }
        return signer.verifySignature(hash, r, s);
    }

    private static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters domain = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH());
    private static final BigInteger HALF_CURVE_ORDER = curve.getN().shiftRight(1);

}
