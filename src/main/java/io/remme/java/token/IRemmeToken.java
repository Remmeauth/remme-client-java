package io.remme.java.token;

import io.remme.java.transactionservice.BaseTransactionResponse;

import java.util.concurrent.Future;

public interface IRemmeToken {
    Future<BaseTransactionResponse> transfer(String publicKeyTo, Long amount);
    Future<Long> getBalance(String publicKeyTo);
}
