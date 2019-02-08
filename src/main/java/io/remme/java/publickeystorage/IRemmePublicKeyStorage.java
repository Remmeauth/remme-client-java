package io.remme.java.publickeystorage;

import io.remme.java.publickeystorage.dto.PublicKeyInfo;
import io.remme.java.publickeystorage.dto.PublicKeyStore;
import io.remme.java.transactionservice.BaseTransactionResponse;

import java.util.concurrent.Future;

public interface IRemmePublicKeyStorage {
    Future<BaseTransactionResponse> store(PublicKeyStore data);

    Future<Boolean> check(String address);

    Future<PublicKeyInfo> getInfo(String address);

    Future<BaseTransactionResponse> revoke(String address);

    Future<String[]> getAccountPublicKeys(String accountPublicKey);
}
