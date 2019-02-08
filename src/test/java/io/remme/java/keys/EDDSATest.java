package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.utils.Functions;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class EDDSATest {
    @Test
    public void testEDDSA() {
        KeyPair keyPair = EDDSA.generateKeyPair();
        PublicKey publicKey = Functions.getPublicKeyFromBytesArray(KeyType.EdDSA, keyPair.getPublic().getEncoded());
        Assert.assertTrue(publicKey instanceof EdDSAPublicKey);
        PrivateKey privateKey = Functions.getPrivateKeyFromBytesArray(KeyType.EdDSA, keyPair.getPrivate().getEncoded());
        Assert.assertTrue(privateKey instanceof EdDSAPrivateKey);
        EDDSA eddsa = new EDDSA(keyPair.getPrivate(), keyPair.getPublic());
        String signature = eddsa.sign("testData", null);
        boolean result = eddsa.verify(signature, "testData", null);
        Assert.assertTrue(result);
    }
}
