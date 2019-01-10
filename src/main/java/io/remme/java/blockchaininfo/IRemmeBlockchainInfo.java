package io.remme.java.blockchaininfo;

import io.remme.java.blockchaininfo.dto.BlockInfo;
import io.remme.java.blockchaininfo.dto.NetworkStatus;
import io.remme.java.blockchaininfo.dto.batches.Batch;
import io.remme.java.blockchaininfo.dto.batches.BatchList;
import io.remme.java.blockchaininfo.dto.blocks.Block;
import io.remme.java.blockchaininfo.dto.blocks.BlockList;
import io.remme.java.blockchaininfo.dto.query.BaseQuery;
import io.remme.java.blockchaininfo.dto.query.StateQuery;
import io.remme.java.blockchaininfo.dto.state.State;
import io.remme.java.blockchaininfo.dto.state.StateList;
import io.remme.java.blockchaininfo.dto.transactions.Transaction;
import io.remme.java.blockchaininfo.dto.transactions.TransactionData;
import io.remme.java.blockchaininfo.dto.transactions.TransactionList;

import java.util.concurrent.Future;

public interface IRemmeBlockchainInfo {
    Future<TransactionList> getTransactions(BaseQuery query);

    Future<Transaction> getTransactionById(String id);

    Object parseTransactionPayload(TransactionData transaction);

    Future<BlockList> getBlocks(BaseQuery query);

    Future<Block> getBlockById(String id);

    Future<BlockInfo[]> getBlockInfo(BaseQuery query);

    Future<BatchList> getBatches(BaseQuery query);

    Future<Batch> getBatchById(String id);

    Future<String> getBatchStatus(String batchId);

    Future<StateList> getState(StateQuery query);

    Future<State> getStateByAddress(String address);

    Object parseStateData(State state);

    Future<String[]> getPeers();

    // getReceipts(ids: string[]): Promise<string[]>;
    Future<NetworkStatus> getNetworkStatus();
}
