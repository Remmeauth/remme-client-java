package io.remme.java.publickeystorage;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.keys.IRemmeKeys;
import io.remme.java.keys.RemmeKeys;
import io.remme.java.publickeystorage.dto.PublicKeyCreate;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.RemmeTransactionService;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static io.remme.java.websocket.RemmeWebSocketTest.MAPPER;

public class RemmePublicStorageTest {
    @Test
    public void testStorage() throws ExecutionException, InterruptedException, JsonProcessingException {
//        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io:8080", false);
        RemmeApi remmeApi = new RemmeApi("138.197.204.63:8080", false);
        //0399cc573facb6a206cd1a6bbb6b3200a80b9147890f1a612feab1d522cd3cebd6
        RemmeAccount remmeAccount = new RemmeAccount("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff");
        RemmeTransactionService transactionService = new RemmeTransactionService(remmeApi, remmeAccount);
        IRemmeKeys keys = RemmeKeys.construct(KeyType.RSA, null, null);
        System.out.println(keys.getPublicKeyPem());
        System.out.println(keys.getAddress());
        RemmePublicKeyStorage publicKeyStorage = new RemmePublicKeyStorage(remmeApi, remmeAccount, transactionService);
        BaseTransactionResponse storeResponse = publicKeyStorage.createAndStore(PublicKeyCreate.builder()
                .data("store data")
                .rsaSignaturePadding(RSASignaturePadding.PSS)
                .validFrom((int)Math.floor(new Date().getTime() / 1000d))
                .validTo((int)Math.floor(new Date(System.currentTimeMillis() + 5*24*60*60000L).getTime() / 1000d))
                .keys(keys)
                .build()).get();
        storeResponse.connectToWebSocket((err, res) -> {
            try {
                if (err != null) {
                    System.out.println(MAPPER.writeValueAsString(err));
                    return;
                }
                System.out.println(MAPPER.writeValueAsString(res));
                storeResponse.closeWebSocket();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(5000L);
//a23be1e18aa0109eb7311226bd4946f6faa6c839c76603db73e4a93bf9bc7fc8fdc713
//        boolean keyIsValid = publicKeyStorage.check("a23be1e18aa0109eb7311226bd4946f6faa6c839c76603db73e4a93bf9bc7fc8fdc713").get();
//        System.out.println(keyIsValid); // true
//
//        PublicKeyInfo publicKeyInfo = publicKeyStorage.getInfo("a23be1e18aa0109eb7311226bd4946f6faa6c839c76603db73e4a93bf9bc7fc8fdc713").get();
//        System.out.println(MAPPER.writeValueAsString(publicKeyInfo)); // PublicKeyInfo
//        BaseTransactionResponse revoke = publicKeyStorage.revoke(keys.getAddress()).get();
//        // You can connectToWebSocket like in store method.
//        System.out.println(revoke.getBatchId()); // string{\^[a-f0-9]{128}$\}
//        revoke.connectToWebSocket((err, res) -> {
//            try {
//                if (err != null) {
//                    System.out.println(MAPPER.writeValueAsString(err));
//                    return;
//                }
//                System.out.println(MAPPER.writeValueAsString(res));
//            } catch (JsonProcessingException e) {
//                System.out.println(MAPPER.writeValueAsString(e.getMessage()));
//            }
//            revoke.closeWebSocket();
//        });
//        Thread.sleep(30000L);
        String[] publicKeyAddresses = publicKeyStorage.getAccountPublicKeys(remmeAccount.getAddress()).get();
        System.out.println(Arrays.toString(publicKeyAddresses)); // string[]
    }
}
