package io.remme.java.certificate;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.certificate.dto.CertificateTransactionResponse;
import io.remme.java.certificate.dto.CreateCertificateDTO;
import io.remme.java.publickeystorage.RemmePublicKeyStorage;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.RemmeTransactionService;
import io.remme.java.utils.Certificate;
import io.remme.java.utils.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static io.remme.java.websocket.RemmeWebSocketTest.MAPPER;

public class RemmeCertificateTest {
    @Test
    public void testCertificate() throws ExecutionException, InterruptedException, JsonProcessingException {
        RemmeAccount account = new RemmeAccount("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff");
//        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io", 8080, false);
        RemmeApi remmeApi = new RemmeApi("138.197.204.63", 8080, false);
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
        System.out.println(Functions.certificateToPEM(cert, true));
        CertificateTransactionResponse response = (CertificateTransactionResponse)certificate.store(cert).get();
        response.connectToWebSocket((err, res) -> {
            try {
                if (err != null) {
                    System.out.println(MAPPER.writeValueAsString(err));
                    return;
                }
                System.out.println(MAPPER.writeValueAsString(res));
//                response.closeWebSocket();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(5000L);
        Assert.assertTrue(certificate.check(Functions.certificateToPEM(cert, true)).get()); // true
        System.out.println(MAPPER.writeValueAsString(certificate.getInfo(Functions.certificateToPEM(cert, true)).get()));
        BaseTransactionResponse response2 = certificate.revoke(cert).get();
        response2.connectToWebSocket((err, res) -> {
            try {
                if (err != null) {
                    System.out.println(MAPPER.writeValueAsString(err));
                    return;
                }
                System.out.println(MAPPER.writeValueAsString(res));
//                response2.closeWebSocket();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(30000L);
        Assert.assertFalse(certificate.check(cert).get()); //false
    }
}
