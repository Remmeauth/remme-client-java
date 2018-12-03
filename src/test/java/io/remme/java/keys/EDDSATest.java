package io.remme.java.keys;

import io.remme.java.keys.EDDSA;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;

public class EDDSATest {
    @Test
    public void testEDDSA() {
        KeyPair keyPair = EDDSA.generateKeyPair();
        EDDSA eddsa = new EDDSA(keyPair.getPrivate(), keyPair.getPublic());
        String signature = eddsa.sign("testData", null);
        boolean result = eddsa.verify(signature, "testData", null);
        Assert.assertTrue(result);
    }
}
