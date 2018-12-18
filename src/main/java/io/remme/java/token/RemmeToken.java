package io.remme.java.token;

import io.remme.java.api.IRemmeApi;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.protobuf.AccountOuterClass;
import io.remme.java.protobuf.Transaction;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.IRemmeTransactionService;
import io.remme.java.transactionservice.dto.CreateTransactionDto;
import io.remme.java.utils.models.PublicKeyRequest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static io.remme.java.utils.Functions.checkAddress;

/**
 * Class that work with tokens.
 * Transfer them and getting balance by public key.
 *
 */
public class RemmeToken implements IRemmeToken {

    private IRemmeApi remmeApi;
    private IRemmeTransactionService remmeTransaction;
    private RemmeFamilyName familyName = RemmeFamilyName.ACCOUNT;
    private String familyVersion = "0.1";

    /**
     * @param remmeApi         {@link io.remme.java.api.RemmeApi}
     * @param remmeTransaction {@link io.remme.java.account.RemmeAccount}
     *                         <pre>
     *                         RemmeApi remmeApi = new RemmeApi();
     *                         RemmeAccount remmeAccount = new RemmeAccount();
     *                         RemmeTransactionService remmeTransaction = new RemmeTransactionService(remmeApi, remmeAccount);
     *                         RemmeToken remmeToken = new RemmeToken(remmeApi, remmeTransaction);
     *                         </pre>
     */
    public RemmeToken(IRemmeApi remmeApi, IRemmeTransactionService remmeTransaction) {
        this.remmeApi = remmeApi;
        this.remmeTransaction = remmeTransaction;
    }

    /**
     * Transfer tokens from signed address {@link io.remme.java.account.RemmeAccount} to given address.
     * Send transaction to REMChain.
     *
     * @param addressTo address to send to
     * @param amount    amount to send
     *                  <pre>
     *                  String someAccountPublicKeyInHex = "02926476095ea28904c11f22d0da20e999801a267cd3455a00570aa1153086eb13";
     *                  String someRemmeAddress = Functions.generateAddress(RemmeFamilyName.ACCOUNT, someAccountPublicKeyInHex);
     *                  <p>
     *                  BaseTransactionResponse transactionResult = transfer(someRemmeAddress, 10).get();
     *                  System.out.println("Sending tokens...BatchId: " + transactionResult.getBatchId());
     *                  <p>
     *                  SocketEventListener transactionCallback = (err, result) -> {
     *                  try {
     *                      if (error != null) {
     *                          System.out.println(MAPPER.writeValueAsString(error));
     *                              return;
     *                      }
     *                          System.out.println(MAPPER.writeValueAsString(result));
     *                  if (result instanceOf BatchInfoDTO && result.getStatus().equals(BatchStatus.COMMITTED)) {
     *                      Long newBalance = remmeToken.getBalance(someRemmeAddress).get();
     *                      System.out.println("Account " + someRemmeAddress + "balance - " + newBalance + " REM");
     *                      transactionResult.closeWebSocket();
     *                  } catch (JsonProcessingException e) {
     *                      e.printStackTrace();
     *                  }
     *                  <p>
     *                  transactionResult.connectToWebSocket(transactionCallback);
     *                  </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> transfer(String addressTo, Long amount) {
        try {
            checkAddress(addressTo);
            if (amount == null) {
                throw new Error("Amount was not provided, please set the amount");
            }
            if (amount <= 0L) {
                throw new Error("Amount must be higher than 0");
            }

            AccountOuterClass.TransferPayload transferPayload = AccountOuterClass.TransferPayload.newBuilder()
                    .setAddressTo(addressTo)
                    .setValue(amount).build();
            Transaction.TransactionPayload transactionPayload = Transaction.TransactionPayload.newBuilder()
                    .setMethod(AccountOuterClass.AccountMethod.Method.TRANSFER.getNumber())
                    .setData(transferPayload.toByteString()).build();

            CreateTransactionDto dto = CreateTransactionDto.builder()
                    .familyName(this.familyName.getName())
                    .familyVersion(this.familyVersion)
                    .inputs(new String[]{addressTo})
                    .outputs(new String[]{addressTo})
                    .payloadBytes(transactionPayload.toByteArray())
                    .build();
            Future<String> transaction = this.remmeTransaction.create(dto);
            return this.remmeTransaction.send(transaction.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get balance on given account address
     *
     * @param address address to get balance for
     * <pre>
     * Long balance = remmetoken.getBalance(remmeAccount.getAddress());
     * System.out.println("Account " + remmeAccount.getAddress() " as sender, balance - " + balance + REM");
     * </pre>
     * @return {@link Long} amount
     */
    public Future<Long> getBalance(String address) {
        checkAddress(address);
        return this.remmeApi.sendRequest(RemmeMethod.TOKEN, new PublicKeyRequest(address), Long.class);
    }
}