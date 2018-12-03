package io.remme.java.keys;

import io.remme.java.keys.ECDSA;
import org.junit.Assert;
import org.junit.Test;

import java.security.*;

public class ECDSATest {
    @Test
    public void testECDSA() {
        KeyPair keyPair = ECDSA.generateKeyPair();
        ECDSA ecdsa = new ECDSA(keyPair.getPrivate(), keyPair.getPublic());
        String signature = ecdsa.sign("testData", null);
        boolean result = ecdsa.verify(signature, "testData", null);
        Assert.assertTrue(result);
    }
}
