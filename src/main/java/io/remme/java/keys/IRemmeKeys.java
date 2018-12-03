package io.remme.java.keys;

import io.remme.java.enums.RSASignaturePadding;

public interface IRemmeKeys {
    /**
     * Sign provided data with selected key implementation
     * @param data Data string which will be signed
     * @param rsaSignaturePadding RSA padding for signature (optional)
     * @return HEX String for signature
     */
    String sign(String data, RSASignaturePadding rsaSignaturePadding);

    /**
     * Verify signature for selected key implementation
     * @param signature HEX String of signature
     * @param data Data string which will be verified
     * @param rsaSignaturePadding RSA padding for signature (optional)
     * @return <code>true</code> in case signature is correct
     */
    boolean verify(String signature, String data, RSASignaturePadding rsaSignaturePadding);
}
