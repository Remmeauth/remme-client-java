package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.utils.Functions;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class ECDSATest {
    @Test
    public void testECDSA() {
        KeyPair keyPair = ECDSA.generateKeyPair();
        PublicKey publicKey = Functions.getPublicKeyFromBytesArray(KeyType.ECDSA, Functions.hexToBytes(Functions.ecdsaPublicKeyToHex(keyPair.getPublic(), true)));
        Assert.assertTrue(publicKey instanceof BCECPublicKey);
        PrivateKey privateKey = Functions.getPrivateKeyFromBytesArray(KeyType.ECDSA, Functions.hexToBytes(Functions.ecdsaPrivateKeyToHex(keyPair.getPrivate())));
        Assert.assertTrue(privateKey instanceof BCECPrivateKey);
        ECDSA ecdsa = new ECDSA(keyPair.getPrivate(), keyPair.getPublic());
        System.out.println(ecdsa.getPublicKeyHex());
        System.out.println(ecdsa.getPrivateKeyHex());
        String signature = ecdsa.sign("testData".getBytes(StandardCharsets.UTF_8), null);
        boolean result = ecdsa.verify(signature, "testData".getBytes(StandardCharsets.UTF_8), null);
        Assert.assertTrue(result);
    }
}
