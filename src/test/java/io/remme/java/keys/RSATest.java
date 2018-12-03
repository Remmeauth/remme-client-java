package io.remme.java.keys;

import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.RSA;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;

public class RSATest {

    @Test
    public void testGenerateKeyPair() {
        KeyPair keyPair = RSA.generateKeyPair(null);
        String signature = new RSA(keyPair.getPublic(), keyPair.getPrivate()).sign("testData", RSASignaturePadding.PSS);
        boolean result = new RSA(keyPair.getPublic(), keyPair.getPrivate()).verify(signature, "testData", RSASignaturePadding.PSS);
        Assert.assertTrue(result);
    }
}
