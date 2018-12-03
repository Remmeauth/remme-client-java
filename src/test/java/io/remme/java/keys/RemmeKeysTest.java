package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.util.concurrent.ExecutionException;

public class RemmeKeysTest {
    @Test
    public void testRemmeKeys() {
        try {
            IRemmeKeysParams params = RemmeKeys.construct(KeyType.RSA, null, null);
            KeyPair keyPair = RemmeKeys.generateKeyPair(KeyType.ECDSA, null).get();
            Assert.assertNotNull("publicKey not null", params.getPublicKey());
            Assert.assertNotNull(RemmeKeys.getAddressFromPublicKey(KeyType.ECDSA, keyPair.getPublic()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
