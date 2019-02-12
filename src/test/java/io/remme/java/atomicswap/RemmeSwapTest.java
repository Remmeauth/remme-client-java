package io.remme.java.atomicswap;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.atomicswap.dto.SwapInfoDTO;
import io.remme.java.atomicswap.dto.SwapInitDTO;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.RemmeTransactionService;
import io.remme.java.websocket.dto.batch.BatchInfoDTO;
import io.remme.java.websocket.dto.batch.BatchStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static io.remme.java.api.IRemmeApi.MAPPER;

public class RemmeSwapTest {
    private static boolean finish = false;
    private static boolean success = false;

    @Test
    public void testSwap() throws ExecutionException, InterruptedException {
        //RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io:8080", false);
        RemmeApi remmeApi = new RemmeApi("node-1-testnet-dev.remme.io:8080", false);
        RemmeAccount remmeAccount = new RemmeAccount("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff");
        RemmeTransactionService remmeTransaction = new RemmeTransactionService(remmeApi, remmeAccount);
        RemmeSwap remmeSwap = new RemmeSwap(remmeApi, remmeTransaction);
        String swapId = "133102e41346242476b15a3a7966eb5249271025fc7fb0b37ed3fdb4bcce3811";
        String secretKey = "039eaa877ff63694f8f09c8034403f8b5165a7418812a642396d5d539f90b170";
        String secretLock = "b605112c2d7489034bbd7beab083fb65ba02af787786bb5e3d99bb26709f4f68";

        long start = System.currentTimeMillis();
        BaseTransactionResponse init = remmeSwap.init(SwapInitDTO.builder()
                .receiverAddress("112007484def48e1c6b77cf784aeabcac51222e48ae14f3821697f4040247ba01558b1")
                .senderAddressNonLocal("0xe6ca0e7c974f06471759e9a05d18b538c5ced11e")
                .amount(100L)
                .swapId(swapId)
                .createdAt((int) Math.floor(System.currentTimeMillis() / 1000)).build()).get();
        init.connectToWebSocket((error, result) -> {
            try {
                if (error != null) {
                    //init error
                    System.out.println(MAPPER.writeValueAsString(error));
                } else if (BatchStatus.COMMITTED.equals(((BatchInfoDTO) result).getStatus())) {
                    //data init
                    System.out.println(MAPPER.writeValueAsString(result));
                    SwapInfoDTO res = remmeSwap.getInfo(swapId).get();
                    //after init info
                    System.out.println(MAPPER.writeValueAsString(res));
                    String pubkey = remmeSwap.getPublicKey().get();
                    System.out.println(pubkey);
                    init.closeWebSocket();
                    BaseTransactionResponse setSecretLock = remmeSwap.setSecretLock(swapId, secretLock).get();
                    setSecretLock.connectToWebSocket((error1, result1) -> {
                        try {
                            if (error1 != null) {
                                //set secret lock error
                                System.out.println(error1);
                            }
                            //data set secret lock
                            System.out.println(MAPPER.writeValueAsString(result1));
                            if (BatchStatus.COMMITTED.equals(((BatchInfoDTO) result1).getStatus())) {
                                SwapInfoDTO res1 = remmeSwap.getInfo(swapId).get();
                                //after set secret lock info
                                System.out.println(MAPPER.writeValueAsString(res1));
                                setSecretLock.closeWebSocket();
                                BaseTransactionResponse approve = remmeSwap.approve(swapId).get();
                                approve.connectToWebSocket((err, data) -> {
                                    try {
                                        if (err != null) {
                                            //approve error
                                            System.out.println(MAPPER.writeValueAsString(err));
                                        }
                                        //approve data
                                        System.out.println(MAPPER.writeValueAsString(data));
                                        if (BatchStatus.COMMITTED.equals(((BatchInfoDTO) data).getStatus())) {
                                            SwapInfoDTO res2 = remmeSwap.getInfo(swapId).get();
                                            //after approve info
                                            System.out.println(MAPPER.writeValueAsString(res2));
                                            approve.closeWebSocket();
                                            BaseTransactionResponse close = remmeSwap.close(swapId, secretKey).get();
                                            close.connectToWebSocket((err1, data1) -> {
                                                try {
                                                    if (err1 != null) {
                                                        //close error
                                                        System.out.println(MAPPER.writeValueAsString(err1));
                                                    }
                                                    //close data
                                                    System.out.println(MAPPER.writeValueAsString(data1));
                                                    if (BatchStatus.COMMITTED.equals(((BatchInfoDTO) data1).getStatus())) {
                                                        SwapInfoDTO res3 = remmeSwap.getInfo(swapId).get();
                                                        //after close info
                                                        System.out.println(MAPPER.writeValueAsString(res3));
                                                        finish = true;
                                                        success = true;
                                                        System.out.println("done");
                                                        close.closeWebSocket();
                                                    }
                                                } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            });
                                        }
                                    } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                        } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        while(!finish && !(System.currentTimeMillis() - start > 100000)) {
            Thread.sleep(5000L);
        }
        Assert.assertTrue(success);
    }
}
