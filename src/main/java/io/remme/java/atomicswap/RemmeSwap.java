package io.remme.java.atomicswap;

import com.google.protobuf.ByteString;
import io.remme.java.api.IRemmeApi;
import io.remme.java.atomicswap.dto.SwapInfoDTO;
import io.remme.java.atomicswap.dto.SwapInitDTO;
import io.remme.java.atomicswap.dto.SwapRequestDTO;
import io.remme.java.enums.Patterns;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.protobuf.AtomicSwap;
import io.remme.java.protobuf.Transaction;
import io.remme.java.transactionservice.BaseTransactionResponse;
import io.remme.java.transactionservice.IRemmeTransactionService;
import io.remme.java.transactionservice.RemmeTransactionService;
import io.remme.java.transactionservice.dto.CreateTransactionDto;
import io.remme.java.utils.RemmeExecutorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static io.remme.java.utils.Functions.generateAddress;
import static io.remme.java.utils.Functions.generateSettingsAddress;

/**
 * Main class for working with atomic swap
 *
 */
public class RemmeSwap implements IRemmeSwap {

    private IRemmeApi remmeApi;
    private IRemmeTransactionService remmeTransactionService;
    private RemmeFamilyName familyName = RemmeFamilyName.SWAP;
    private String familyVersion = "0.1";
    private String zeroAddress = StringUtils.repeat("0", 70);
    private String blockInfoNamespaceAddress = "00b10c00";
    private String blockInfoConfigAddress = "00b10c01" + StringUtils.repeat("0", 62);
    private String settingsKeyGenesisOwners = generateSettingsAddress("remme.settings.genesis_owners");
    private String settingsSwapComission = generateSettingsAddress("remme.settings.swap_comission");

    private byte[] generateTransactionPayload(AtomicSwap.AtomicSwapMethod.Method method, ByteString data) {
        return Transaction.TransactionPayload.newBuilder()
                .setMethod(method.getNumber())
                .setData(data).build().toByteArray();
    }

    private Addresses getAddresses(AtomicSwap.AtomicSwapMethod.Method method, String swapId, String receiverAddress) {
        String addresses = generateAddress(this.familyName.getName(), swapId);
        String[] inputs = null;
        String[] outputs = null;
        switch (method) {
            case INIT:
                inputs = new String[]{
                        addresses,
                        this.settingsSwapComission,
                        this.zeroAddress,
                        this.blockInfoNamespaceAddress,
                        this.blockInfoConfigAddress,
                        this.settingsKeyGenesisOwners};
                outputs = new String[]{
                        addresses,
                        this.settingsSwapComission,
                        this.zeroAddress,
                        this.settingsKeyGenesisOwners};
                break;
            case EXPIRE:
                inputs = new String[]{
                        addresses,
                        this.zeroAddress,
                        this.blockInfoNamespaceAddress,
                        this.blockInfoConfigAddress,
                        this.settingsKeyGenesisOwners};
                outputs = new String[]{
                        addresses,
                        this.zeroAddress,
                        this.settingsKeyGenesisOwners};
                break;
            case CLOSE:
                inputs = new String[]{
                        addresses,
                        receiverAddress,
                        this.zeroAddress,
                        this.settingsKeyGenesisOwners};
                outputs = new String[]{
                        addresses,
                        receiverAddress,
                        this.zeroAddress,
                        this.settingsKeyGenesisOwners};
                break;
        }
        Addresses io;
        if (inputs != null) {
            io = new Addresses(inputs, outputs);
        } else {
            io = new Addresses(new String[]{addresses}, new String[]{addresses});
        }
        return io;
    }

    private Future<BaseTransactionResponse> createAndSendTransaction(byte[] transactionPayload, String[] inputs, String[] outputs) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> {
            String transaction = this.remmeTransactionService.create(CreateTransactionDto.builder()
                    .familyName(this.familyName.getName())
                    .familyVersion(this.familyVersion)
                    .inputs(inputs)
                    .outputs(outputs)
                    .payloadBytes(transactionPayload).build()).get();
            return this.remmeTransactionService.send(transaction).get();
        });
    }

    private void checkParameters(Parameters parameters) {
        try {
            Field[] fields = parameters.getClass().getDeclaredFields();
            for (Field field : fields) {
                Object result = field.get(parameters);
                if (result instanceof String) {
                    String value = (String) result;
                    if (value != null) {
                        if (StringUtils.isEmpty(value)) {
                            throw new RemmeValidationException("The " + field.getName() + " was missing in parameters");
                        }
                        if (!value.matches(Patterns.SWAP_ID.getPattern())) {
                            throw new RemmeValidationException("Given " + field.getName() + " is not valid");
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param remmeApi                {@link io.remme.java.api.RemmeApi}
     * @param remmeTransactionService {@link RemmeTransactionService}
     * <pre>
     * RemmeApi remmeApi = new RemmeApi();
     * RemmeAccount remmeAccount = new RemmeAccount();
     * RemmeTransactionService remmeTransaction = new RemmeTransactionService(remmeApi, remmeAccount);
     * RemmeSwap remmeSwap = new RemmeSwap(remmeApi, remmeTransaction);
     * </pre>
     */
    public RemmeSwap(IRemmeApi remmeApi, IRemmeTransactionService remmeTransactionService) {
        this.remmeApi = remmeApi;
        this.remmeTransactionService = remmeTransactionService;
    }

    /**
     * Approve swap with given id.
     * Send transaction into REMChain.
     *
     * @param swapId string id
     *               <pre>
     *               BaseTransactionResponse approve = remmeSwap.approve(swapId).get();
     *               System.out.println(approve.getBatchId())
     *               </pre>
     *               @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> approve(String swapId) {
        checkParameters(new Parameters(swapId, null, null));
        AtomicSwap.AtomicSwapApprovePayload payload = AtomicSwap.AtomicSwapApprovePayload.newBuilder().setSwapId(swapId)
                .build();
        byte[] transactionPayload = generateTransactionPayload(AtomicSwap.AtomicSwapMethod.Method.APPROVE, payload.toByteString());
        Addresses addresses = getAddresses(AtomicSwap.AtomicSwapMethod.Method.APPROVE, swapId, null);
        return createAndSendTransaction(transactionPayload, addresses.inputs, addresses.outputs);
    }

    /**
     * Close swap with given id and secret key for checking who can close swap.
     * Send transaction into REMChain.
     *
     * @param swapId string id
     * @param secretKey string key
     * <pre>
     * BaseTransactionResponse close = remmeSwap.close(swapId);
     * System.out.println(close.getBatchId());
     * </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> close(String swapId, String secretKey) {
        checkParameters(new Parameters(swapId, null, secretKey));
        try {
            SwapInfoDTO infoDTO = getInfo(swapId).get();
            AtomicSwap.AtomicSwapClosePayload payload = AtomicSwap.AtomicSwapClosePayload.newBuilder()
                    .setSwapId(swapId)
                    .setSecretKey(secretKey).build();
            byte[] transactionPayload = generateTransactionPayload(AtomicSwap.AtomicSwapMethod.Method.CLOSE, payload.toByteString());
            Addresses addresses = getAddresses(AtomicSwap.AtomicSwapMethod.Method.CLOSE, swapId, infoDTO.getReceiver_address());
            return createAndSendTransaction(transactionPayload, addresses.inputs, addresses.outputs);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Expire swap with given id. Could be expired after 24h after initiation.
     * Send transaction into REMChain.
     *
     * @param swapId string id
     * <pre>
     * BaseTransactionResponse expire = remmeSwap.expire(swapId);
     * System.out.println(expire.getBatchId());
     * </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> expire(String swapId) {
        checkParameters(new Parameters(swapId, null, null));
        AtomicSwap.AtomicSwapExpirePayload payload = AtomicSwap.AtomicSwapExpirePayload.newBuilder()
                .setSwapId(swapId).build();
        byte[] transactionPayload = generateTransactionPayload(AtomicSwap.AtomicSwapMethod.Method.EXPIRE, payload.toByteString());
        Addresses addresses = getAddresses(AtomicSwap.AtomicSwapMethod.Method.EXPIRE, swapId, null);
        return createAndSendTransaction(transactionPayload, addresses.inputs, addresses.outputs);
    }

    /**
     * Get info about swap by given swap id.
     *
     * @param swapId string id
     * <pre>
     * SwapInfoDTO info = remmeSwap.getInfo(swapId);
     * System.out.println(info.getState());
     * </pre>
     * @return {@link SwapInfoDTO}
     */
    public Future<SwapInfoDTO> getInfo(String swapId) {
        checkParameters(new Parameters(swapId, null, null));
        return remmeApi.sendRequest(RemmeMethod.ATOMIC_SWAP, new SwapRequestDTO(swapId),
                SwapInfoDTO.class);
    }

    /**
     * Get swap public key.
     *
     * <pre>
     * String publicKey = remmeSwap.getPublicKey();
     * System.out.println(publicKey);
     * </pre>
     * @return string public key
     */
    public Future<String> getPublicKey() {
        return remmeApi.sendRequest(RemmeMethod.ATOMIC_SWAP_PUBLIC_KEY, String.class);
    }

    /**
     * Initiation of swap.
     * Send transaction into REMChain.
     *
     * @param data {@link SwapInitDTO}
     * <pre>
     * RemmeApi remmeApi = new RemmeApi();
     *         RemmeAccount remmeAccount = new RemmeAccount("631a5f4e73efa194944fef2456ed743c6cf06211e68a18909e67023a5910a2ff");
     *         RemmeTransactionService remmeTransaction = new RemmeTransactionService(remmeApi, remmeAccount);
     *         RemmeSwap remmeSwap = new RemmeSwap(remmeApi, remmeTransaction);
     *         String swapId = "133102e41346242476b15a3a7966eb5249271025fc7fb0b37ed3fdb4bcce3811";
     *         String secretKey = "039eaa877ff63694f8f09c8034403f8b5165a7418812a642396d5d539f90b170";
     *         String secretLock = "b605112c2d7489034bbd7beab083fb65ba02af787786bb5e3d99bb26709f4f68";
     *
     *         BaseTransactionResponse init = remmeSwap.init(SwapInitDTO.builder()
     *                 .receiverAddress("112007484def48e1c6b77cf784aeabcac51222e48ae14f3821697f4040247ba01558b1")
     *                 .senderAddressNonLocal("0xe6ca0e7c974f06471759e9a05d18b538c5ced11e")
     *                 .amount(100L)
     *                 // you can provide secret lock in initiation.
     *                 .secretLockBySolicitor(secretLock)
     *                 // or you can set it separately in {@link #setSecretLock(String, String)}
     *                 .swapId(swapId)
     *                 .createdAt((int) Math.floor(System.currentTimeMillis() / 1000)).build()).get();
     *         System.out.println(init.getBatchId());
     * </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> init(SwapInitDTO data) {
        AtomicSwap.AtomicSwapInitPayload payload = AtomicSwap.AtomicSwapInitPayload.newBuilder()
                .setSwapId(data.getSwapId())
                .setAmount(data.getAmount())
                .setCreatedAt(data.getCreatedAt())
                .setEmailAddressEncryptedByInitiator(data.getEmailAddressEncryptedByInitiator() == null ? "" : data.getEmailAddressEncryptedByInitiator())
                .setReceiverAddress(data.getReceiverAddress())
                .setSecretLockBySolicitor(data.getSecretLockBySolicitor() == null ? "" : data.getSecretLockBySolicitor())
                .setSenderAddressNonLocal(data.getSenderAddressNonLocal())
                .build();
        byte[] transactionPayload = this.generateTransactionPayload(AtomicSwap.AtomicSwapMethod.Method.INIT, payload.toByteString());
        Addresses addresses = getAddresses(AtomicSwap.AtomicSwapMethod.Method.INIT, data.getSwapId(), null);
        return createAndSendTransaction(transactionPayload, addresses.inputs, addresses.outputs);
    }

    /**
     * Set secret lock to swap with given swap id.
     * Send transaction into REMChain.
     *
     * @param swapId string id
     * @param secretLock string lock
     * <pre>
     * String swapId = "133102e41346242476b15a3a7966eb5249271025fc7fb0b37ed3fdb4bcce3806";
     * String secretLockBySolicitor = "b605112c2d7489034bbd7beab083fb65ba02af787786bb5e3d99bb26709f4f68";
     * BaseTransactionResponse setting = remmeSwap.setSecretLock(swapId, secretLockBySolicitor);
     * System.out.println(setting.getBatchId());
     * </pre>
     * @return {@link BaseTransactionResponse}
     */
    public Future<BaseTransactionResponse> setSecretLock(String swapId, String secretLock) {
        checkParameters(new Parameters(swapId, secretLock, null));
        AtomicSwap.AtomicSwapSetSecretLockPayload payload = AtomicSwap.AtomicSwapSetSecretLockPayload.newBuilder()
                .setSwapId(swapId)
                .setSecretLock(secretLock).build();
        byte[] transactionPayload = generateTransactionPayload(AtomicSwap.AtomicSwapMethod.Method.SET_SECRET_LOCK,
                payload.toByteString());
        Addresses addresses = getAddresses(AtomicSwap.AtomicSwapMethod.Method.SET_SECRET_LOCK, swapId, null);
        return createAndSendTransaction(transactionPayload, addresses.inputs, addresses.outputs);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private class Addresses {
        String[] inputs;
        String[] outputs;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private class Parameters {
        public String swapId;
        public String secretLock;
        public String secretKey;
    }
}