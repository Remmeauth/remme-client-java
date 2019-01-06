package io.remme.java.blockchaininfo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import io.remme.java.api.IRemmeApi;
import io.remme.java.blockchaininfo.dto.BlockInfo;
import io.remme.java.blockchaininfo.dto.NetworkStatus;
import io.remme.java.blockchaininfo.dto.PeerList;
import io.remme.java.blockchaininfo.dto.batches.Batch;
import io.remme.java.blockchaininfo.dto.batches.BatchList;
import io.remme.java.blockchaininfo.dto.blocks.Block;
import io.remme.java.blockchaininfo.dto.blocks.BlockList;
import io.remme.java.blockchaininfo.dto.query.BaseQuery;
import io.remme.java.blockchaininfo.dto.query.BaseQueryRequest;
import io.remme.java.blockchaininfo.dto.query.StateQuery;
import io.remme.java.blockchaininfo.dto.query.StateQueryRequest;
import io.remme.java.blockchaininfo.dto.state.State;
import io.remme.java.blockchaininfo.dto.state.StateList;
import io.remme.java.blockchaininfo.dto.transactions.Transaction;
import io.remme.java.blockchaininfo.dto.transactions.TransactionData;
import io.remme.java.blockchaininfo.dto.transactions.TransactionList;
import io.remme.java.enums.Patterns;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.protobuf.AccountOuterClass;
import io.remme.java.protobuf.AtomicSwap;
import io.remme.java.protobuf.PubKey;
import io.remme.java.utils.RemmeExecutorService;
import io.remme.java.websocket.dto.RemmeRequestParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Main class that works with blockchain data. Blocks, batches, transactions, addresses, peers.
 */
public class RemmeBlockchainInfo implements IRemmeBlockchainInfo {
    private IRemmeApi remmeApi;

    private static Map<String, ParserEntity> address = new HashMap<>();

    static {
        address.put(RemmeFamilyName.SWAP.getNamespace(), new ParserEntity("info atomic swap", AtomicSwap.AtomicSwapInfo.parser()));
        address.put(RemmeFamilyName.ACCOUNT.getNamespace(), new ParserEntity("account", AccountOuterClass.Account.parser()));
        address.put(RemmeFamilyName.PUBLIC_KEY.getNamespace(), new ParserEntity("storage public key", PubKey.PubKeyStorage.parser()));
    }

    private static Map<RemmeFamilyName, Map<Integer, ParserEntity>> correspond = new HashMap<>();

    static {
        Map<Integer, ParserEntity> swapMethods = new HashMap<>();
        swapMethods.put(AtomicSwap.AtomicSwapMethod.Method.INIT_VALUE, new ParserEntity("atomic-swap-init", AtomicSwap.AtomicSwapInitPayload.parser()));
        swapMethods.put(AtomicSwap.AtomicSwapMethod.Method.APPROVE_VALUE, new ParserEntity("atomic-swap-approve", AtomicSwap.AtomicSwapApprovePayload.parser()));
        swapMethods.put(AtomicSwap.AtomicSwapMethod.Method.EXPIRE_VALUE, new ParserEntity("atomic-swap-expire", AtomicSwap.AtomicSwapExpirePayload.parser()));
        swapMethods.put(AtomicSwap.AtomicSwapMethod.Method.SET_SECRET_LOCK_VALUE, new ParserEntity("atomic-swap-set-secret-lock", AtomicSwap.AtomicSwapSetSecretLockPayload.parser()));
        swapMethods.put(AtomicSwap.AtomicSwapMethod.Method.CLOSE_VALUE, new ParserEntity("atomic-swap-close", AtomicSwap.AtomicSwapClosePayload.parser()));
        correspond.put(RemmeFamilyName.SWAP, swapMethods);
        Map<Integer, ParserEntity> accountMethods = new HashMap<>();
        accountMethods.put(AccountOuterClass.AccountMethod.Method.TRANSFER_VALUE, new ParserEntity("transfer token", AccountOuterClass.TransferPayload.parser()));
        accountMethods.put(AccountOuterClass.AccountMethod.Method.GENESIS_VALUE, new ParserEntity("genesis", AccountOuterClass.GenesisPayload.parser()));
        correspond.put(RemmeFamilyName.ACCOUNT, accountMethods);
        Map<Integer, ParserEntity> pubKeyMethods = new HashMap<>();
        pubKeyMethods.put(PubKey.PubKeyMethod.Method.STORE_VALUE, new ParserEntity("store public key", PubKey.NewPubKeyPayload.parser()));
        pubKeyMethods.put(PubKey.PubKeyMethod.Method.REVOKE_VALUE, new ParserEntity("revoke public key", PubKey.RevokePubKeyPayload.parser()));
        correspond.put(RemmeFamilyName.PUBLIC_KEY, pubKeyMethods);
    }

    private void checkId(String id) {
        if (id == null || id.isEmpty() || !id.matches(Patterns.HEADER_SIGNATURE.getPattern())) {
            throw new RemmeValidationException("Given 'id' is not valid");
        }
    }

    private void checkAddress(String address) {
        if (address == null || address.isEmpty() || !address.matches(Patterns.ADDRESS.getPattern())) {
            throw new RemmeValidationException("Given 'address' is not valid");
        }
    }

    /**
     * @param remmeApi {@link io.remme.java.api.RemmeApi}
     * <pre>
     * IRemmeApi remmeApi = new RemmeApi();
     * RemmeBlockchainInfo remmeBlockchainInfo = new RemmeBlockchainInfo(remmeApi);
     * </pre>
     */
    public RemmeBlockchainInfo(IRemmeApi remmeApi) {
        this.remmeApi = remmeApi;
    }

    /**
     * Get all blocks from REMChain.
     * You can specify one or more query parameters.
     *
     * @param query {@link BaseQuery}
     * Example with query
     * <pre>
     * BlockList blocks = remmeBlockchainInfo.getBlocks().get();
     * System.out.println(blocks.getData().length);
     * </pre>
     *
     * Start from specifying block number
     * <pre>
     * BlockList blocks = remmeBlockchainInfo.getBlocks(BaseQuery.builder().start(4).build()).get();
     * System.out.println(blocks.getData().length);
     * </pre>
     *
     * Reverse output
     * <pre>
     * BlockList blocks = remmeBlockchainInfo.getBlocks(BaseQuery.builder().reverse(true).build()).get();
     * System.out.println(blocks.getData().length);
     * </pre>
     *
     * Specify limit of output
     * <pre>
     * BlockList blocks = remmeBlockchainInfo.getBlocks(BaseQuery.builder().limit(2).build()).get();
     * System.out.println(blocks.getData().length);
     * </pre>
     *
     * Specify head of block for start
     * <pre>
     * BlockList blocks = remmeBlockchainInfo.getBlocks(BaseQuery.builder().head(
     * "9d2dc2ab673d028bc1dd8b5be8d2d885e4383a827cd0261f58334252bf807c08113207eabbd12d0786d6bba5378a791129f9c520c17597b5504d4b547ef57491"
     * ).build()).get();
     * System.out.println(blocks); // BlockList
     * </pre>
     * @return {@link BlockList}
     */
    public Future<BlockList> getBlocks(BaseQuery query) {
        if (query != null) {
            if (query.getStart() instanceof Integer) {
                String temp = ("0000000000000000" + new BigInteger(String.valueOf(query.getStart())).toString(16));
                query.setStart("0x" + temp.substring(temp.length() - 16));
            }
        }
        return this.remmeApi.sendRequest(RemmeMethod.BLOCKS, new BaseQueryRequest(query), BlockList.class);
    }

    /**
     * Get block by id (header_signature) from REMChain.
     *
     * @param id header_signature of block
     * <pre>
     * Block block = blockchainInfo.getBlockById("4273c10acd8bb788b7e857799866da245d6f5e2713fc8d16e7983d5dad4ad1372c1f2a68caac2ba14e3e6b746986faef0fce69a31933a55477dc89fcb11db257").get();
     * System.out.println(block.getData().getHeader_signature());
     * </pre>
     * @return {@link Block}
     */
    public Future<Block> getBlockById(String id) {
        checkId(id);
        return this.remmeApi.sendRequest(RemmeMethod.FETCH_BLOCK, RemmeRequestParams.builder().id(id).build(), Block.class);
    }

    /**
     * Get information about block.
     *
     * @param query {@link BaseQuery}
     * Without parameters.
     * <pre>
     * BlockInfo[] blocks = blockchainInfo.getBlockInfo(BaseQuery.builder().build()).get();
     *         System.out.println(blocks.length);
     * </pre>
     * Start from specifying block number.
     * <pre>
     * BlockInfo[] blocks = blockchainInfo.getBlockInfo(BaseQuery.builder().start(2).build()).get();
     *         System.out.println(blocks.length);
     * </pre>
     * Specify limit of output
     * <pre>
     * BlockInfo[] blocks = blockchainInfo.getBlockInfo(BaseQuery.builder().limit(4).build()).get();
     *         System.out.println(blocks.length);
     * </pre>
     * @return array of {@link BlockInfo}
     */
    public Future<BlockInfo[]> getBlockInfo(BaseQuery query) {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> {
            try {
                BlockInfo[] blocks = this.remmeApi.sendRequest(RemmeMethod.BLOCK_INFO, query, BlockInfo[].class).get();
                if (blocks == null) {
                    throw new RemmeValidationException("Unknown error occurs in the server");
                }
                return blocks;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Get all batches from REMChain.
     *
     * @param query {@link BaseQuery}
     * Without parameters
     * <pre>
     * BatchList list = blockchainInfo.getBatches(BaseQuery.builder().build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     *
     * Start from specifying batch header_signature (batch ID).
     * <pre>
     * BatchList list = blockchainInfo.getBatches(BaseQuery.builder().start("2fc21506c76e0177eb52a420ed4a70511d80089261cbaee58617719c9df4b5356e70444549adb20109e27350dd2590f45b9e1a2ea9e829ec26ce1ccff2db1f3e")
     *                 .reverse(false).build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     *
     * Specify limit of output
     * <pre>
     * BatchList list = blockchainInfo.getBatches(BaseQuery.builder().limit(4).build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     *
     * Specify head of block for start
     * <pre>
     * BatchList list = blockchainInfo.getBatches(BaseQuery.builder().head("f650727dad9a402656179904e95144b1ee1dd4b78a696a4d6d6122c82f5b78fe29c07d45d8842e435d2266e58a18c846137d351b840c4d6fed60b1b71edcb3c9")
     *                 .reverse(false).build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     * @return {@link io.remme.java.blockchaininfo.dto.batches.BatchList}
     */
    public Future<BatchList> getBatches(BaseQuery query) {
        return this.remmeApi.sendRequest(RemmeMethod.BATCHES, new BaseQueryRequest(query), BatchList.class);
    }

    /**
     * Get batch by id (header_signature) from REMChain.
     *
     * @param id header_signature of batch
     * <pre>
     * RemmeBlockchainInfo blockchainInfo = initInfo();
     *         Batch batch = blockchainInfo.getBatchById("2fc21506c76e0177eb52a420ed4a70511d80089261cbaee58617719c9df4b5356e70444549adb20109e27350dd2590f45b9e1a2ea9e829ec26ce1ccff2db1f3e").get();
     *         System.out.println(batch.getData().getHeader_signature());
     * </pre>
     * @return {@link Batch}
     */
    public Future<Batch> getBatchById(String id) {
        checkId(id);
        return this.remmeApi.sendRequest(RemmeMethod.FETCH_BATCH, RemmeRequestParams.builder().id(id).build(), Batch.class);
    }

    /**
     * Get status for batch.
     *
     * @param id header_signature for batch
     * <pre>
     * String status = blockchainInfo.getBatchStatus("2fc21506c76e0177eb52a420ed4a70511d80089261cbaee58617719c9df4b5356e70444549adb20109e27350dd2590f45b9e1a2ea9e829ec26ce1ccff2db1f3e").get();
     *         System.out.println(status);
     * </pre>
     * @return String status
     */
    public Future<String> getBatchStatus(String id) {
        checkId(id);
        return this.remmeApi.sendRequest(RemmeMethod.BATCH_STATUS, RemmeRequestParams.builder().id(id).build(), String.class);
    }

    /**
     * Get states in REMChain
     *
     * @param query {@link StateQuery}
     * Without parameters
     * <pre>
     *         StateList list = blockchainInfo.getState(StateQuery.builder().build()).get();
     *         System.out.println(list.getData());
     * </pre>
     * Start from specifying state address
     * <pre>
     * StateList list = blockchainInfo.getState(StateQuery.builder().start("a23be18cbf7258f641658787ff7d7a09091928c6b617db8efac19d333bb882a06387a6")
     *                 .reverse(false).build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     * Specify limit of output
     * <pre>
     * StateList list = blockchainInfo.getState(StateQuery.builder().limit(2).build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     * Specify head of block for start
     * <pre>
     * StateList list = blockchainInfo.getState(StateQuery.builder()
     *                  .head("f650727dad9a402656179904e95144b1ee1dd4b78a696a4d6d6122c82f5b78fe29c07d45d8842e435d2266e58a18c846137d351b840c4d6fed60b1b71edcb3c9")
     *                 .reverse(false).build()).get();
     *         System.out.println(list.getData().length);
     * </pre>
     * @return {@link StateList}
     */
    public Future<StateList> getState(StateQuery query) {
        return this.remmeApi.sendRequest(RemmeMethod.STATE, new StateQueryRequest(query), StateList.class);
    }

    /**
     * Get state by address
     *
     * @param address address in REMChain
     * <pre>
     * State state = blockchainInfo.getStateByAddress("a23be18cbf7258f641658787ff7d7a09091928c6b617db8efac19d333bb882a06387a6").get();
     *         System.out.println(state.getHead());
     * </pre>
     * @return {@link State}
     */
    public Future<State> getStateByAddress(String address) {
        checkAddress(address);
        return this.remmeApi.sendRequest(RemmeMethod.FETCH_STATE, RemmeRequestParams.builder().address(address).build(), State.class);
    }

    /**
     * Parse state data.
     *
     * @param state state from REMChain
     * <pre>
     * String address = "a23be18cbf7258f641658787ff7d7a09091928c6b617db8efac19d333bb882a06387a6";
     * State state = blockchainInfo.getStateByAddress(address).get();
     * state.setAddress(address);
     * RemmeBlockchainInfo.ParsedData obj = (RemmeBlockchainInfo.ParsedData) blockchainInfo.parseStateData(state);
     * System.out.println(obj.getType());
     * </pre>
     * @return parsed data
     */
    public Object parseStateData(State state) {
        try {
            if (state.getAddress() == null || state.getAddress().isEmpty()) {
                throw new RemmeValidationException("State should have address for parsing");
            }
            if (RemmeBlockchainInfo.address.containsKey(state.getAddress().substring(0, 6))) {
                ParserEntity parserEntity = RemmeBlockchainInfo.address.get(state.getAddress().substring(0, 6));
                return new ParsedData(parserEntity.getType(),
                        parserEntity.getParser().parseFrom(Base64.decodeBase64(state.getData())));
            } else {
                throw new RemmeValidationException("This address " + state.getAddress() + " don't supported for parsing");
            }
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all transactions from REMChain.
     *
     * @param query {@link BaseQuery}
     * Without parameters
     * <pre>
     * TransactionList list = blockchainInfo.getTransactions(StateQuery.builder().build()).get();
     *         System.out.println(list.getData());
     * </pre>
     * Start from specifying transactions header_signature.
     * <pre>
     * TransactionList list = blockchainInfo.getTransactions(StateQuery.builder().start("23ba1f0a091557a68562b7a3218c4cbc75a3518b02397458817aeb51c930ca0d669d13e1ba751412ccb5e6d7fdc76045c60039098fb36bcc28d5eb1919c0b2bc")
     *                 .reverse(false).build()).get();
     *         System.out.println(list.getData());
     * </pre>
     * Specify limit of output
     * <pre>
     * TransactionList list = blockchainInfo.getTransactions(StateQuery.builder().limit(10).build()).get();
     *         System.out.println(list.getData());
     * </pre>
     * Specify head of block for start
     * <pre>
     * TransactionList list = blockchainInfo.getTransactions(StateQuery.builder().head("f650727dad9a402656179904e95144b1ee1dd4b78a696a4d6d6122c82f5b78fe29c07d45d8842e435d2266e58a18c846137d351b840c4d6fed60b1b71edcb3c9")
     *                 .reverse(false).build()).get();
     *         System.out.println(list.getData());
     * </pre>
     * @return {@link TransactionList}
     */
    public Future<TransactionList> getTransactions(BaseQuery query) {
        return this.remmeApi.sendRequest(RemmeMethod.TRANSACTIONS, new BaseQueryRequest(query), TransactionList.class);
    }

    /**
     * Get transaction by id (header_signature) from REMChain
     *
     * @param id string header_signature
     * <pre>
     * Transaction transaction = blockchainInfo.getTransactionById("23ba1f0a091557a68562b7a3218c4cbc75a3518b02397458817aeb51c930ca0d669d13e1ba751412ccb5e6d7fdc76045c60039098fb36bcc28d5eb1919c0b2bc").get();
     *         System.out.println(transaction.getHead());
     * </pre>
     * @return {@link Transaction}
     */
    public Future<Transaction> getTransactionById(String id) {
        checkId(id);
        return this.remmeApi.sendRequest(RemmeMethod.FETCH_TRANSACTION, RemmeRequestParams.builder().id(id).build(), Transaction.class);
    }

    /**
     * Parse transaction payload. Take transaction and return object with payload and type
     *
     * @param transaction transaction from REMChain
     * <pre>
     * Transaction transaction = blockchainInfo.getTransactionById("4fe1d33bb31ea7823d3af4f6a34e2f66876728013d4b4e2c9e9a4699338566ba7c9219161268908523786b7e194ebb736f2b9d922c1406d7ee7487da4f0eaa31").get();
     *         System.out.println(transaction.getData());
     *         RemmeBlockchainInfo.ParsedData data = (RemmeBlockchainInfo.ParsedData) blockchainInfo.parseTransactionPayload(transaction.getData());
     *         System.out.println(data.getType());
     * </pre>
     * @return parsed transaction data
     */
    public Object parseTransactionPayload(TransactionData transaction) {
        try {
            RemmeFamilyName familyName = RemmeFamilyName.getByName(transaction.getHeader().getFamily_name());
            if (familyName != null && correspond.containsKey(familyName)) {
                io.remme.java.protobuf.Transaction.TransactionPayload payload = io.remme.java.protobuf.Transaction.TransactionPayload.parser().parseFrom(Base64.decodeBase64(transaction.getPayload()));
                ParserEntity parserEntity = RemmeBlockchainInfo.correspond.get(familyName).get(payload.getMethod());
                return new ParsedData(parserEntity.getType(), parserEntity.getParser().parseFrom(payload.getData()));
            } else {
                throw new RemmeValidationException("This family name (" + transaction.getHeader().getFamily_name() + ") don't supported for parsing");
            }
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get network status for node.
     *
     * <pre>
     * NetworkStatus status = initInfo().getNetworkStatus().get();
     *         System.out.println(status.getPeerCount());
     * </pre>
     * @return {@link NetworkStatus}
     */
    public Future<NetworkStatus> getNetworkStatus() {
        return this.remmeApi.sendRequest(RemmeMethod.NETWORK_STATUS, NetworkStatus.class);
    }

    /**
     * Get peers that connected to this node.
     *
     * <pre>
     * String[] peers = initInfo().getPeers().get();
     *         System.out.println(Arrays.toString(peers));
     * </pre>
     * @return peers string
     */
    public Future<String[]> getPeers() {
        ExecutorService es = RemmeExecutorService.getInstance();
        return es.submit(() -> this.remmeApi.sendRequest(RemmeMethod.PEERS, PeerList.class).get().getData());
    }

    // TODO: uncomment after refactoring receipts
    // /**
    //  * Get transactions receipts
    //  *
    //  * @param {string[]} ids
    //  * @returns {Promise<ReceiptList>}
    //  */
    // public Future<String[]> getReceipts(String[] ids) {
    //     ids.forEach(id -> checkId(id));
    //     ExecutorService es = RemmeExecutorService.getInstance();
    //     es.submit(() -> this.remmeApi.sendRequest(RemmeMethod.RECEIPTS, RemmeRequestParams.builder().id(id))).get().getData();
    // }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParserEntity {
        private String type;
        private Parser<?> parser;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParsedData {
        private String type;
        private Object data;
    }
}
