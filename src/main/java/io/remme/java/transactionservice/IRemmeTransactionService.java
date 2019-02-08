package io.remme.java.transactionservice;

import io.remme.java.transactionservice.dto.CreateTransactionDto;

import java.util.concurrent.Future;

public interface IRemmeTransactionService {
    Future<String> create(CreateTransactionDto settings);
    Future<BaseTransactionResponse> send(String transaction);
}
