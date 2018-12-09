package io.remme.java.account;

import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.keys.ECDSA;
import io.remme.java.keys.IRemmeKeys;
import io.remme.java.utils.Functions;

/**
 * Account that is used for signing transactions and storing public keys which he was signed.
 * <pre>
 * RemmeAccount account = new RemmeAccount("ac124700cc4325cc2a78b22b9acb039d9efe859ef673b871d55d1078391934f9");
 * System.out.println(account.getPrivateKeyHex()); // "ac124700cc4325cc2a78b22b9acb039d9efe859ef673b871d55d1078391934f9";
 *
 * RemmeAccount anotherAccount = new RemmeAccount();
 * System.out.println(anotherAccount.getPrivateKeyHex());
 *
 * String data = "some data";
 * String signedData = account.sign(data);
 *
 * boolean isVerify = account.verify(signedData, data);
 * System.out.println(isVerify); // true
 *
 * boolean isVerifyInAnotherAccount = anotherAccount.verify(signedData, data);
 * System.out.println(isVerifyInAnotherAccount); // false
 * </pre>
 */
public class RemmeAccount extends ECDSA implements IRemmeKeys {

    /**
     * Get private key, create signer by using private key,
     * generate public key from private key and generate account address by using public key and family name
     * <a target="_top" href="https://docs.remme.io/remme-core/docs/family-account.html#addressing">Addressing</a>
     *
     * Get private key;
     * <pre>
     * RemmeAccount account = new RemmeAccount("ac124700cc4325cc2a78b22b9acb039d9efe859ef673b871d55d1078391934f9");
     * System.out.println(account.getPrivateKeyHex()); // "ac124700cc4325cc2a78b22b9acb039d9efe859ef673b871d55d1078391934f9";
     * </pre>
     *
     * @param privateKeyHex private key in HEX format
     */
    public RemmeAccount(String privateKeyHex) {
        super(Functions.generateECDSAPrivateKey(Functions.hexToBytes(privateKeyHex)), null);
        this.familyName = RemmeFamilyName.ACCOUNT;
        this.address = Functions.generateAddress(familyName.getName(), this.publicKeyHex);
    }


    /**
     * Generate private key, create signer by using private key,
     * generate public key from private key and generate account address by using public key and family name
     * <a target="_top" href="https://docs.remme.io/remme-core/docs/family-account.html#addressing">Addressing</a>
     *
     * Generate new private key;
     * <pre>
     * RemmeAccount account = new RemmeAccount();
     * System.out.println(account.getPrivateKeyHex());
     * </pre>
     *
     */
    public RemmeAccount() {
        this(Functions.ecdsaPrivateKeyToHex(ECDSA.generateKeyPair().getPrivate()));
    }
}
