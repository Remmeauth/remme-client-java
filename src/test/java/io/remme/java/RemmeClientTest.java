package io.remme.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.remme.java.api.NetworkConfig;
import io.remme.java.certificate.dto.CertificateTransactionResponse;
import io.remme.java.certificate.dto.CreateCertificateDTO;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.utils.Certificate;
import io.remme.java.utils.Functions;
import io.remme.java.websocket.dto.batch.BatchInfoDTO;
import io.remme.java.websocket.dto.batch.BatchStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static io.remme.java.websocket.RemmeWebSocketTest.MAPPER;

public class RemmeClientTest {
    private static boolean finish = false;

    @Test
    public void testClient() {
        RemmeClient client = new RemmeClient();
        Assert.assertNotNull(client.getAccount().getAddress());
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException, JsonProcessingException {
        RemmeClient client = new RemmeClient(ClientInit.builder().privateKeyHex("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff")
                .networkConfig(new NetworkConfig("138.197.204.63:8080", false)).build());
        Certificate cert = client.getCertificate().create(CreateCertificateDTO.builder()
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
        CertificateTransactionResponse response = (CertificateTransactionResponse) client.getCertificate().store(cert).get();
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
        Assert.assertTrue(client.getCertificate().check(Functions.certificateToPEM(cert, true)).get()); // true
        System.out.println(MAPPER.writeValueAsString(client.getCertificate().getInfo(Functions.certificateToPEM(cert, true)).get()));
        BaseTransactionResponse response2 = client.getCertificate().revoke(cert).get();
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
        Assert.assertFalse(client.getCertificate().check(cert).get()); //false
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        RemmeClient client = new RemmeClient(ClientInit.builder().privateKeyHex("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff")
                .networkConfig(new NetworkConfig("138.197.204.63:8080", false)).build());
        String accountToSendAddress = RemmeClient.generateAccount().getAddress();
        Long balance = client.getToken().getBalance(accountToSendAddress).get();
        System.out.println("before:" + balance);
        BaseTransactionResponse result = client.getToken().transfer(accountToSendAddress, 100L).get();
        result.connectToWebSocket((err, res) -> {
            try {
                if (err != null) {
                    System.out.println(new ObjectMapper().writeValueAsString(err));
                }
                if (res != null && BatchStatus.COMMITTED.equals(((BatchInfoDTO) res).getStatus())) {
                    System.out.println(new ObjectMapper().writeValueAsString(res));
                    result.closeWebSocket();
                    Long balance2 = client.getToken().getBalance(accountToSendAddress).get();
                    System.out.println("after:" + balance2);
                    finish = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        while (!finish) {
            Thread.sleep(5000L);
        }
    }
}
