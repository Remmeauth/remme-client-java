package io.remme.java.publickeystorage;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.enums.KeyType;
import io.remme.java.enums.RSASignaturePadding;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.keys.IRemmeKeys;
import io.remme.java.keys.RemmeKeys;
import io.remme.java.publickeystorage.dto.PublicKeyStore;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.RemmeTransactionService;
import io.remme.java.utils.Functions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static io.remme.java.websocket.RemmeWebSocketTest.MAPPER;

public class RemmePublicStorageTest {
    @Test
    public void testStorage() throws ExecutionException, InterruptedException {
        RemmeApi remmeApi = new RemmeApi("138.197.204.63", 8080, false);
        //0399cc573facb6a206cd1a6bbb6b3200a80b9147890f1a612feab1d522cd3cebd6
        RemmeAccount remmeAccount = new RemmeAccount("31e0386248657ff01070e93dc2cc2cf8531b193494a1b1ed9ff9e482b4c4c33d");
        RemmeTransactionService transactionService = new RemmeTransactionService(remmeApi, remmeAccount);
//        IRemmeKeys keys = RemmeKeys.construct(KeyType.RSA, null, null);
//        System.out.println(keys.getPublicKeyPem());
        RemmePublicKeyStorage publicKeyStorage = new RemmePublicKeyStorage(remmeApi, remmeAccount, transactionService);
//        BaseTransactionResponse storeResponse = publicKeyStorage.store(PublicKeyStore.builder()
//                .data("store data")
//                .rsaSignaturePadding(RSASignaturePadding.PSS)
//                .validFrom(new Date())
//                .validTo(new Date(System.currentTimeMillis() + 5*24*60*60000L))
//                .keys(keys)
//                .build()).get();
//        storeResponse.connectToWebSocket((err, res) -> {
//            try {
//                if (err != null) {
//                    System.out.println(MAPPER.writeValueAsString(err));
//                    return;
//                }
//                System.out.println(MAPPER.writeValueAsString(res));
//                storeResponse.closeWebSocket();
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        while (true) {
//
//        }
//        boolean keyIsValid = publicKeyStorage.check(keys.getAddress()).get();
//        System.out.println(keyIsValid); // true
//
//        PublicKeyInfo publicKeyInfo = publicKeyStorage.getInfo(keys.getAddress()).get();
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
