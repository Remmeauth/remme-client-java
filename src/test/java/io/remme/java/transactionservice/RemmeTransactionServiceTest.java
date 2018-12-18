package io.remme.java.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.transactionservice.dto.CreateTransactionDto;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import static io.remme.java.websocket.RemmeWebSocketTest.MAPPER;

public class RemmeTransactionServiceTest {
    @Test
    public void testCreateAndSign() throws ExecutionException, InterruptedException {
//        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io", 8080, false);
        RemmeApi remmeApi = new RemmeApi("138.197.204.63", 8080, false);
        RemmeAccount remmeAccount = new RemmeAccount("b2c94353072abecabd47db2ca03a72f7a9eef583e1aec4f4e76c67a4f8a5f252");
//        RemmeAccount remmeAccount = new RemmeAccount("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562");
        String signature = remmeAccount.sign("my transaction");
        boolean verif = remmeAccount.verify("fcdc36310949b5bd6316a63f11b45a4adee2f5b24108e1167df0030a67a9044e239160a32a52c97ccd5de61c295279430fb40d8888491eb33a7ad50798eb26de",
                "my transaction");
//        Assert.assertTrue(verif);
        RemmeTransactionService transactionService = new RemmeTransactionService(remmeApi, remmeAccount);
        CreateTransactionDto dto = CreateTransactionDto.builder()
                .familyName(RemmeFamilyName.PUBLIC_KEY.getName())
                .familyVersion("0.1")
                .inputs(new String[]{})
                .outputs(new String[]{})
                .payloadBytes("my transaction".getBytes(StandardCharsets.UTF_8))
                .build();
        String transaction = transactionService.create(dto).get();
        BaseTransactionResponse response = transactionService.send(transaction).get();
        response.connectToWebSocket((error, result) -> {
            try {
                if (error != null) {
                    System.out.println(MAPPER.writeValueAsString(error));
                    return;
                }
                System.out.println(MAPPER.writeValueAsString(result));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        System.out.println(response.getBatchId());
        while(true) {

        }
    }
}
