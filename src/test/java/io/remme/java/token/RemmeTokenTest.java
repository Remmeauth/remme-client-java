package io.remme.java.token;

import io.remme.java.account.RemmeAccount;
import io.remme.java.api.RemmeApi;
import io.remme.java.transactionservice.RemmeTransactionService;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RemmeTokenTest {
    @Test
    public void testBalance() throws ExecutionException, InterruptedException {
        RemmeAccount remmeAccount = new RemmeAccount("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff");
//        RemmeAccount remmeAccount2 = new RemmeAccount("cbda109323487371d21e0a9ee138f5a9ece3fe12b82ee5256a6ee51e25201562");
        RemmeApi remmeApi = new RemmeApi("138.197.204.63", 8080, false);
        RemmeTransactionService transactionService = new RemmeTransactionService(remmeApi, remmeAccount);
        RemmeToken token = new RemmeToken(remmeApi, transactionService);
        Long result = token.getBalance(remmeAccount.getAddress()).get();
        System.out.println(result);
        Assert.assertTrue(result > 0);
//        RemmeTransactionService transactionService2 = new RemmeTransactionService(remmeApi, remmeAccount2);
//        RemmeToken token2 = new RemmeToken(remmeApi, transactionService2);
//        Long result2 = token2.getBalance(remmeAccount2.getAddress()).get();
//        System.out.println(result2);
    }
 }
