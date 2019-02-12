package io.remme.java.blockchaininfo;

import io.remme.java.api.RemmeApi;
import io.remme.java.blockchaininfo.dto.BlockInfo;
import io.remme.java.blockchaininfo.dto.NetworkStatus;
import io.remme.java.blockchaininfo.dto.batches.Batch;
import io.remme.java.blockchaininfo.dto.batches.BatchList;
import io.remme.java.blockchaininfo.dto.blocks.Block;
import io.remme.java.blockchaininfo.dto.blocks.BlockList;
import io.remme.java.blockchaininfo.dto.query.StateQuery;
import io.remme.java.blockchaininfo.dto.state.State;
import io.remme.java.blockchaininfo.dto.state.StateList;
import io.remme.java.blockchaininfo.dto.transactions.Transaction;
import io.remme.java.blockchaininfo.dto.transactions.TransactionList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class RemmeBlockchainInfoTest {
    private RemmeBlockchainInfo initInfo() {
        //                RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io:8080", false);
        RemmeApi remmeApi = new RemmeApi("node-1-testnet-dev.remme.io:8080", false);
        return new RemmeBlockchainInfo(remmeApi);
    }

    @Test
    public void testBlockchainInfo() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
        BlockList list = blockchainInfo.getBlocks(StateQuery.builder().limit(2).reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getBlocks(StateQuery.builder().start(4).build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getBlocks(StateQuery.builder().head(list.getHead()).build()).get();
        Assert.assertTrue(list.getData().length > 0);
    }

    @Test
    public void testBlock() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
        Block block = blockchainInfo.getBlockById("4273c10acd8bb788b7e857799866da245d6f5e2713fc8d16e7983d5dad4ad1372c1f2a68caac2ba14e3e6b746986faef0fce69a31933a55477dc89fcb11db257").get();
        Assert.assertNotNull(block.getData().getHeader_signature());
    }

    @Test
    public void testBlockInfo() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
//        BlockInfo[] blocks = blockchainInfo.getBlockInfo(BaseQuery.builder().build()).get();
//        System.out.println(blocks.length);
        BlockInfo[] blocks = blockchainInfo.getBlockInfo(StateQuery.builder().start(2).build()).get();
        Assert.assertEquals(2, blocks.length);
        blocks = blockchainInfo.getBlockInfo(StateQuery.builder().limit(4).build()).get();
        Assert.assertEquals(4, blocks.length);
    }

    @Test
    public void testBatchList() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
//        BatchList list = blockchainInfo.getBatches(BaseQuery.builder().build()).get();
//        System.out.println(list.getData().length);
        BatchList list = blockchainInfo.getBatches(StateQuery.builder().limit(4).build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getBatches(StateQuery.builder().start("2fc21506c76e0177eb52a420ed4a70511d80089261cbaee58617719c9df4b5356e70444549adb20109e27350dd2590f45b9e1a2ea9e829ec26ce1ccff2db1f3e")
                .reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getBatches(StateQuery.builder().head("f650727dad9a402656179904e95144b1ee1dd4b78a696a4d6d6122c82f5b78fe29c07d45d8842e435d2266e58a18c846137d351b840c4d6fed60b1b71edcb3c9")
                .reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
    }

    @Test
    public void testBatch() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
        Batch batch = blockchainInfo.getBatchById("2fc21506c76e0177eb52a420ed4a70511d80089261cbaee58617719c9df4b5356e70444549adb20109e27350dd2590f45b9e1a2ea9e829ec26ce1ccff2db1f3e").get();
        Assert.assertNotNull(batch.getData().getHeader_signature());
        String status = blockchainInfo.getBatchStatus("2fc21506c76e0177eb52a420ed4a70511d80089261cbaee58617719c9df4b5356e70444549adb20109e27350dd2590f45b9e1a2ea9e829ec26ce1ccff2db1f3e").get();
        Assert.assertNotNull(status);
    }

    @Test
    public void testStateList() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
//        StateList list = blockchainInfo.getState(StateQuery.builder().build()).get();
//        System.out.println(list.getData());
        StateList list = blockchainInfo.getState(StateQuery.builder().limit(50).build()).get();
        Assert.assertEquals(50, list.getData().length);
        list = blockchainInfo.getState(StateQuery.builder().address("0000007ca83d6bbb759da9cde0fb0dec1400c55cc3bbcd6b1243b2e3b0c44298fc1c14")
                .build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getState(StateQuery.builder().start("a23be18cbf7258f641658787ff7d7a09091928c6b617db8efac19d333bb882a06387a6")
                .reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getState(StateQuery.builder().head("f650727dad9a402656179904e95144b1ee1dd4b78a696a4d6d6122c82f5b78fe29c07d45d8842e435d2266e58a18c846137d351b840c4d6fed60b1b71edcb3c9")
                .reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
    }

    @Test
    public void testState() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
        String address = "a23be18cbf7258f641658787ff7d7a09091928c6b617db8efac19d333bb882a06387a6";
        State state = blockchainInfo.getStateByAddress(address).get();
        state.setAddress(address);
        Assert.assertNotNull(state.getHead());
        RemmeBlockchainInfo.ParsedData obj = (RemmeBlockchainInfo.ParsedData) blockchainInfo.parseStateData(state);
        Assert.assertNotNull(obj.getType());
    }

    @Test
    public void testTransactionList() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
//        TransactionList list = blockchainInfo.getTransactions(StateQuery.builder().build()).get();
//        System.out.println(list.getData());
        TransactionList list = blockchainInfo.getTransactions(StateQuery.builder().limit(10).build()).get();
        Assert.assertEquals(10, list.getData().length);
        list = blockchainInfo.getTransactions(StateQuery.builder().start("23ba1f0a091557a68562b7a3218c4cbc75a3518b02397458817aeb51c930ca0d669d13e1ba751412ccb5e6d7fdc76045c60039098fb36bcc28d5eb1919c0b2bc")
                .reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
        list = blockchainInfo.getTransactions(StateQuery.builder().head("f650727dad9a402656179904e95144b1ee1dd4b78a696a4d6d6122c82f5b78fe29c07d45d8842e435d2266e58a18c846137d351b840c4d6fed60b1b71edcb3c9")
                .reverse(false).build()).get();
        Assert.assertTrue(list.getData().length > 0);
    }

    @Test
    public void testTransaction() throws ExecutionException, InterruptedException {
        RemmeBlockchainInfo blockchainInfo = initInfo();
        Transaction transaction = blockchainInfo.getTransactionById("4fe1d33bb31ea7823d3af4f6a34e2f66876728013d4b4e2c9e9a4699338566ba7c9219161268908523786b7e194ebb736f2b9d922c1406d7ee7487da4f0eaa31").get();
        Assert.assertNotNull(transaction.getData());
        RemmeBlockchainInfo.ParsedData data = (RemmeBlockchainInfo.ParsedData) blockchainInfo.parseTransactionPayload(transaction.getData());
        Assert.assertNotNull(data.getType());
    }

    @Test
    public void testNetworkStatus() throws ExecutionException, InterruptedException {
        NetworkStatus status = initInfo().getNetworkStatus().get();
        System.out.println(status.getPeerCount());
    }

    @Test
    public void testPeers() throws ExecutionException, InterruptedException {
        String[] peers = initInfo().getPeers().get();
        Assert.assertNotNull(Arrays.toString(peers));
    }
 }
