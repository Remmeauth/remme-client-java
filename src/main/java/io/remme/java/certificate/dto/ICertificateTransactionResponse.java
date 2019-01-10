package io.remme.java.certificate.dto;

import io.remme.java.keys.IRemmeKeys;
import io.remme.java.protobuf.PubKey;
import io.remme.java.utils.Certificate;

public interface ICertificateTransactionResponse {
    IRemmeKeys getKeys();

    Certificate getCertificate();

    String sign(String data, PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding);

    String sign(String data);

    boolean verify(String data, String signedData, PubKey.NewPubKeyPayload.RSAConfiguration.Padding rsaSignaturePadding);

    boolean verify(String data, String signedData);
}
