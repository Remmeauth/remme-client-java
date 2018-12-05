package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.utils.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;

public class RSATest {

    @Test
    public void testGenerateKeyPair() throws IOException {
        PublicKey object = Functions.getPublicKeyFromPEM("-----BEGIN PUBLIC KEY-----\r\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhdw64WKrvXCWtGsNeVT\r\nPKDPpcHN0kcF4acvfPauDE8TpIFu8rFQdnGdBldJMo+iHC4VkEc7SqP0Z7bynBXZ\r\nze6YAsi7VUggO+5kDuJnKrg0VJ5swfV/Jdvj9ev1iG1TeVTAyp1Uvjmek9uAh6Dg\r\nobdtWM/VpVYsbBcMT4XXpzmuv0qkEf9YmR3kJ5SBGdkb6jaOnjJWO0O6kOUO54y0\r\n6wr0BXqYWWQTnGC3DJf2iqu68CeoZsg/dRNs1zXP4x00GyOW7OdnmMUsySquf//K\r\nHUlnD3Oa1TyWzjF6NcMWv0PgDg6u8q4739X0ueBNDpXJyiMMpQUZ/8YbW/Ijdfv7\r\nDQIDAQAB\r\n-----END PUBLIC KEY-----\r\n");
        byte[] key = Functions.getPublicKeyFromPEM(new File("C:/Users/pc/Documents/keys/public_test")).getEncoded();
        Assert.assertEquals(object.getEncoded().length, key.length);
        KeyPair keyPair = RSA.generateKeyPair(null);
        PrivateKey privateKey = Functions.getPrivateKeyFromBytesArray(KeyType.RSA, keyPair.getPrivate().getEncoded());
        Assert.assertTrue(privateKey instanceof RSAPrivateKey);
        String signature = new RSA(keyPair.getPublic(), keyPair.getPrivate()).sign("testData", RSASignaturePadding.PSS);
        boolean result = new RSA(keyPair.getPublic(), keyPair.getPrivate()).verify(signature, "testData", RSASignaturePadding.PSS);
        Assert.assertTrue(result);
    }
}
