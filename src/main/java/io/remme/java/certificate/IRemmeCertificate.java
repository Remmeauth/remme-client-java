package io.remme.java.certificate;

import io.remme.java.certificate.dto.CreateCertificateDTO;
import io.remme.java.certificate.dto.ICertificateTransactionResponse;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.publickeystorage.dto.PublicKeyInfo;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.utils.Certificate;

import java.util.concurrent.Future;

public interface IRemmeCertificate {
    Future<Certificate> create(CreateCertificateDTO dto);

    Future<ICertificateTransactionResponse> createAndStore(CreateCertificateDTO dto);

    Future<ICertificateTransactionResponse> store(Certificate certificate);

    Future<Boolean> check(Certificate certificate);

    Future<PublicKeyInfo> getInfo(Certificate certificate);

    Future<BaseTransactionResponse> revoke(Certificate certificate);

    String sign(Certificate certificate, String data, RSASignaturePadding rsaSignaturePadding);

    boolean verify(Certificate certificate, String data, String signatureData, RSASignaturePadding rsaSignaturePadding);

    Future<ICertificateTransactionResponse> store(String certificatePem);

    Future<Boolean> check(String certificatePem);

    Future<PublicKeyInfo> getInfo(String certificatePem);

    Future<BaseTransactionResponse> revoke(String certificatePem);

    String sign(String certificatePem, String data, RSASignaturePadding rsaSignaturePadding);

    boolean verify(String certificatePem, String data, String signatureData, RSASignaturePadding rsaSignaturePadding);
}