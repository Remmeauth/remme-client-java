package io.remme.java.keys;

import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.protobuf.PubKey;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface IRemmeKeys {
    PublicKey getPublicKey();
    PrivateKey getPrivateKey();
    KeyType getKeyType();
    String getPublicKeyPem();
    String getPublicKeyHex();
    String getAddress();

    /**
     * Sign provided data with selected key implementation
     * @param data Data string which will be signed
     * @param rsaSignaturePadding RSA padding for signature (optional)
     * @return HEX String for signature
     */
    String sign(String data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding);

    /**
     * Sign provided data with selected key implementation
     * @param data Data string which will be signed
     * @return HEX String for signature
     */
    String sign(String data);

    /**
     * Verify signature for selected key implementation
     * @param signature HEX String of signature
     * @param data Data string which will be verified
     * @param rsaSignaturePadding RSA padding for signature (optional)
     * @return <code>true</code> in case signature is correct
     */
    boolean verify(String signature, String data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding);

    /**
     * Verify signature for selected key implementation
     * @param signature HEX String of signature
     * @param data Data string which will be verified
     * @return <code>true</code> in case signature is correct
     */
    boolean verify(String signature, String data);
}
