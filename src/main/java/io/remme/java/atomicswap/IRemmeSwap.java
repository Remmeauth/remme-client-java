package io.remme.java.atomicswap;

import io.remme.java.atomicswap.dto.SwapInfoDTO;
import io.remme.java.atomicswap.dto.SwapInitDTO;
import io.remme.java.transactionservice.BaseTransactionResponse;

import java.util.concurrent.Future;

public interface IRemmeSwap {
    Future<BaseTransactionResponse> init(SwapInitDTO data);
    Future<BaseTransactionResponse> approve(String swapId);
    Future<BaseTransactionResponse> expire(String swapId);
    Future<BaseTransactionResponse> setSecretLock(String swapId, String secretLock);
    Future<BaseTransactionResponse> close(String swapId, String secretLock);
    Future<SwapInfoDTO> getInfo(String swapId);
    Future<String> getPublicKey();
}
