package io.remme.java.certificate.dto;

import io.remme.java.enums.RSASignaturePadding;

public interface ICertificateTransactionResponse {
    String sign(String data, RSASignaturePadding rsaSignaturePadding);

    String sign(String data);

    boolean verify(String data, String signedData, RSASignaturePadding rsaSignaturePadding);

    boolean verify(String data, String signedData);
}
