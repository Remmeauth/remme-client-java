package io.remme.java.certificate.dto;

import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.IRemmeKeys;
import io.remme.java.utils.Certificate;

public interface ICertificateTransactionResponse {
    IRemmeKeys getKeys();

    Certificate getCertificate();

    String sign(String data, RSASignaturePadding rsaSignaturePadding);

    String sign(String data);

    boolean verify(String data, String signedData, RSASignaturePadding rsaSignaturePadding);

    boolean verify(String data, String signedData);
}
