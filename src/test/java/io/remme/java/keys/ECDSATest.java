package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.utils.Functions;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.junit.Assert;
import org.junit.Test;

import java.security.*;

public class ECDSATest {
    @Test
    public void testECDSA() {
        KeyPair keyPair = ECDSA.generateKeyPair();
        PublicKey publicKey = Functions.getPublicKeyFromBytesArray(KeyType.ECDSA, keyPair.getPublic().getEncoded());
        Assert.assertTrue(publicKey instanceof BCECPublicKey);
        PrivateKey privateKey = Functions.getPrivateKeyFromBytesArray(KeyType.ECDSA, keyPair.getPrivate().getEncoded());
        Assert.assertTrue(privateKey instanceof BCECPrivateKey);
        ECDSA ecdsa = new ECDSA(keyPair.getPrivate(), keyPair.getPublic());
        String signature = ecdsa.sign("testData", null);
        boolean result = ecdsa.verify(signature, "testData", null);
        Assert.assertTrue(result);
    }
}
