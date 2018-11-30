package io.remme.java.keys;

import io.remme.java.enums.RSASignaturePadding;

public interface IRemmeKeys {
        Object sign(String data, RSASignaturePadding rsaSignaturePadding);
        boolean verify(String signature, String data);
}
