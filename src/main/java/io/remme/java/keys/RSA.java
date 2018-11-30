package io.remme.java.keys;

import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.dto.KeyDTO;

public class RSA extends KeyDTO implements  IRemmeKeys {

    private static final Integer RSA_KEY_SIZE = 2048;

    private Integer calculateSaltLength(String md) {
        int emlen = (int) Math.ceil(RSA_KEY_SIZE / 8);
        return emlen - md.length() - 2;
    }

    @Override
    public Object sign(String data, RSASignaturePadding rsaSignaturePadding) {
        return null;
    }

    @Override
    public boolean verify(String signature, String data) {
        return false;
    }
}
