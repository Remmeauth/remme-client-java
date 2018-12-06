package io.remme.java.account;

import io.remme.java.keys.ECDSA;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.junit.Assert;
import org.junit.Test;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.SecureRandom;

public class RemmeAccountTest {
    @Test
    public void testRemmeAccount() throws DecoderException {

        byte[] bytes = new byte[32];
        SecureRandom random = new SecureRandom();
        do {
            random.nextBytes(bytes);
        } while (!(new BigInteger(bytes).compareTo(BigInteger.ZERO) > 0));
        BigInteger privateKey = new BigInteger(Hex.decodeHex("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562"));
        String pvkHex = Hex.encodeHexString(((BCECPrivateKey)ECDSA.generatePrivateKey(Hex.decodeHex("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562"))).getS().toByteArray());
        Assert.assertEquals("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562", pvkHex);

        PrivateKey key = ECDSA.generatePrivateKey(Hex.decodeHex("ac124700cc4325cc2a78b22b9acb039d9efe859ef673b871d55d1078391934f9".toUpperCase()));
        do {
            random.nextBytes(bytes);
        } while (!(new BigInteger(bytes).compareTo(BigInteger.ZERO) > 0));
        RemmeAccount account = new RemmeAccount("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562");
        System.out.println(account.getPrivateKeyHex()); // "ac124700cc4325cc2a78b22b9acb039d9efe859ef673b871d55d1078391934f9";

        RemmeAccount anotherAccount = new RemmeAccount();
        System.out.println(anotherAccount.getPrivateKeyHex());
        ECDSA ecdsa = new ECDSA(ECDSA.generatePrivateKey(Hex.decodeHex("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562")), ECDSA.getPublicKeyFromBytes(Sign.publicKeyFromPrivate(privateKey).toString(16)));
        String data = "some data";
        String signedData = account.sign(data);
        String signedData2 = ecdsa.sign(data);
        boolean isVerify = account.verify(signedData, data);
        boolean isVerify2 = account.verify(signedData2, data);
        boolean isVerify3 = ecdsa.verify(signedData, data);
        System.out.println(isVerify); // true
        System.out.println(isVerify2); // true
        System.out.println(isVerify3); // true

        boolean isVerifyInAnotherAccount = anotherAccount.verify(signedData, data);
        System.out.println(isVerifyInAnotherAccount); // false
    }
}
