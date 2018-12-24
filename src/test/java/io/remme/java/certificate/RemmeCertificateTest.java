package io.remme.java.certificate;

import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.certificate.dto.CreateCertificateDTO;
import io.remme.java.publickeystorage.RemmePublicKeyStorage;
import io.remme.java.transactionservice.RemmeTransactionService;
import io.remme.java.utils.Certificate;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RemmeCertificateTest {
    @Test
    public void testCertificate() throws ExecutionException, InterruptedException {
        RemmeAccount account = new RemmeAccount();
        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io", 8080, false);
        RemmeTransactionService transactionService = new RemmeTransactionService(remmeApi, account);
        RemmePublicKeyStorage publicKeyStorage = new RemmePublicKeyStorage(remmeApi, account, transactionService);
        RemmeCertificate certificate = new RemmeCertificate(publicKeyStorage);
        Certificate cert = certificate.create(CreateCertificateDTO.builder()
                .email("test@email.com")
                .commonName("testCert")
                .name("test")
                .title("test")
                .stateName("CA")
                .countryName("USA")
                .validity(365)
                .validAfter(0)
                .build()).get();
        System.out.println("success");
    }
}
