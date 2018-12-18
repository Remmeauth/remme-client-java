package io.remme.java.account;

import io.remme.java.keys.ECDSA;
import io.remme.java.utils.Functions;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class RemmeAccountTest {
    @Test
    public void testRemmeAccount() {
        String privateKeyA = "cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562";
        String publikKeyA = "02c31752280bdb2c8a542dcdd38bb622dae1980068aa2a82e10cae962f5d017175";
        String pvkHex =((BCECPrivateKey)Functions.generateECDSAPrivateKey(Functions.hexToBytes(privateKeyA))).getS().toString(16);
        Assert.assertEquals(privateKeyA, pvkHex);
        String key1 = Functions.compress(Functions.getECDSAPublicKeyFromBytes(Functions.hexToBytes(publikKeyA)));
        Assert.assertEquals(publikKeyA, key1);
        RemmeAccount account = new RemmeAccount(privateKeyA);
        System.out.println(account.getPrivateKeyHex());

        RemmeAccount anotherAccount = new RemmeAccount();
        System.out.println(anotherAccount.getPrivateKeyHex());
        ECDSA ecdsa = new ECDSA(Functions.generateECDSAPrivateKey(Functions.hexToBytes(privateKeyA)), Functions.getECDSAPublicKeyFromBytes(Functions.hexToBytes(publikKeyA)));
        String data = "some data";
        String signedData = account.sign(data);
        String signedData2 = ecdsa.sign(data);
        boolean isVerify = account.verify(signedData, data);
        boolean isVerify2 = account.verify(signedData2, data);
        boolean isVerify3 = ecdsa.verify(signedData, data);
        Assert.assertTrue(isVerify); // true
        Assert.assertTrue(isVerify2); // true
        Assert.assertTrue(isVerify3); // true

        boolean isVerifyInAnotherAccount = anotherAccount.verify(signedData, data);
        Assert.assertFalse(isVerifyInAnotherAccount); // false
    }
}
