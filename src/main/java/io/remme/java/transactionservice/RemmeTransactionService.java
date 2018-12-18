package io.remme.java.transactionservice;

import com.google.protobuf.ByteString;
import io.remme.java.account.RemmeAccount;
import io.remme.java.api.IRemmeApi;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.transactionservice.dto.CreateTransactionDto;
import io.remme.java.transactionservice.dto.SendTransactionDto;
import io.remme.java.utils.RemmeExecutorService;
import io.remme.java.utils.models.NodeConfigRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import sawtooth.sdk.protobuf.Transaction;
import sawtooth.sdk.protobuf.TransactionHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Class for creating and sending transactions
 *
 */
public class RemmeTransactionService implements IRemmeTransactionService {

    private IRemmeApi remmeApi;
    private RemmeAccount remmeAccount;

    /**
     * @param remmeApi     instanse of RemmeApi
     * @param remmeAccount instance of RemmeAccount
     *
     * <pre>
     * RemmeApi remmeApi = new RemmeApi(); // {@link io.remme.java.api.RemmeApi}
     * RemmeAccount remmeAccount = new RemmeAccount(); // {@link RemmeAccount}
     * RemmeTransactionService remmeTransaction = new RemmeTransactionService(remmeApi, remmeAccount);
     *</pre>
     */
    public RemmeTransactionService(IRemmeApi remmeApi, RemmeAccount remmeAccount) {
        this.remmeApi = remmeApi;
        this.remmeAccount = remmeAccount;
    }

    /**
     * Documentation for building transactions
     * <a target="_top" href="https://sawtooth.hyperledger.org/docs/core/releases/latest/_autogen/sdk_submit_tutorial_js.html#building-the-transaction">Building the transaction</a>
     *
     * @param settings {@link CreateTransactionDto} settings for Transaction create
     *<pre>
     *        CreateTransactionDto dto = CreateTransactionDto.builder()
     *                 .familyName(RemmeFamilyName.PUBLIC_KEY.getName())
     *                 .familyVersion("0.1")
     *                 .inputs(new String[]{})
     *                 .outputs(new String[]{})
     *                 .payloadBytes("my transaction".getBytes(StandardCharsets.UTF_8))
     *                 .build();
     * String transaction = remmeTransaction.create(createDto).get();
     * </pre>
     * @return encoded Base64 transaction string
     */
    public Future<String> create(CreateTransactionDto settings) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> {
            try {
                NodeConfigRequest nodeConfig = remmeApi.sendRequest(RemmeMethod.NODE_CONFIG, NodeConfigRequest.class).get();
                List<String> outputs = new ArrayList<>(Arrays.asList(settings.getOutputs()));
                outputs.add(remmeAccount.getAddress());
                List<String> inputs = new ArrayList<>(Arrays.asList(settings.getInputs()));
                inputs.add(remmeAccount.getAddress());
                TransactionHeader transactionHeader = TransactionHeader.newBuilder()
                        .setFamilyName(settings.getFamilyName())
                        .setFamilyVersion(settings.getFamilyVersion())
                        .setSignerPublicKey(remmeAccount.getPublicKeyHex())
                        .setNonce(DigestUtils.sha512Hex(String.valueOf(Math.floor(Math.random() * 1000))))
                        .setBatcherPublicKey(nodeConfig.getNode_public_key())
                        .setPayloadSha512(DigestUtils.sha512Hex(settings.getPayloadBytes()))
                        .addAllOutputs(outputs)
                        .addAllInputs(inputs)
                        .build();

                String signature = remmeAccount.sign(transactionHeader.toByteArray());
                Transaction transaction = Transaction.newBuilder()
                        .setHeader(transactionHeader.toByteString())
                        .setHeaderSignature(signature)
                        .setPayload(ByteString.copyFrom(settings.getPayloadBytes())).build();
                return Base64.encodeBase64String(transaction.toByteArray());
            } catch (Exception e) {
                throw new RemmeValidationException(e);
            }
        });
    }

    /**
     * @param transaction Base64 string
     * <pre>
     * BaseTransactionResponse result = remmeTransaction.send(transaction).get();
     * System.out.println(result.getBatchId());
     * </pre>
     * @return {@link BaseTransactionResponse} Future object
     */
    public Future<BaseTransactionResponse> send(String transaction) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> {
            try {
                SendTransactionDto requestPayload = new SendTransactionDto(transaction);
                String batchId = remmeApi.sendRequest(RemmeMethod.TRANSACTION, requestPayload, String.class).get();
                return new BaseTransactionResponse(
                        this.remmeApi.getNodeAddress(),
                        this.remmeApi.isSslMode(),
                        batchId);
            } catch (Exception e) {
                throw new RemmeValidationException(e);
            }
        });
    }

}
