package io.remme.java.api;

import io.remme.java.enums.RemmeMethod;
import io.remme.java.utils.models.NodeConfigRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RemmeApiTest {

    @Test
    public void getNodeConfig() throws ExecutionException, InterruptedException {
        RemmeApi remmeApi = new RemmeApi("node-1-testnet-dev.remme.io:8080", false);
        NodeConfigRequest nodeConfig = remmeApi.sendRequest(RemmeMethod.NODE_CONFIG, NodeConfigRequest.class).get();
        Assert.assertTrue(nodeConfig.getNode_public_key() != null && !nodeConfig.getNode_public_key().isEmpty());
        Assert.assertTrue(nodeConfig.getStorage_public_key() != null && !nodeConfig.getStorage_public_key().isEmpty());
    }

    @Test
    public void getPrivate() throws ExecutionException, InterruptedException {
//        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io:8080", false);
        RemmeApi remmeApi = new RemmeApi("node-1-testnet-dev.remme.io:8080", false);
        Future<String> result = remmeApi.sendRequest(RemmeMethod.NODE_PRIVATE_KEY, String.class);
        Assert.assertNotNull(result.get());
        System.out.println(result.get());
    }

    @Test
    public void testSendRequestWithParams() throws ExecutionException, InterruptedException {
        RemmeApi remmeApi = new RemmeApi("node-1-testnet-dev.remme.io:8080", false);
        Map<String, Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("limit", 50);
        Future<List> result = remmeApi.sendRequest(RemmeMethod.BLOCK_INFO, params, List.class);
        Assert.assertNotNull(result);
        Assert.assertTrue((result.get()).size() > 0);
    }

    @Test
    public void testSendRequestWithoutParams() throws ExecutionException, InterruptedException {
        RemmeApi remmeApi = new RemmeApi("node-1-testnet-dev.remme.io:8080", false);
        Future<Integer> result = remmeApi.sendRequest(RemmeMethod.BLOCK_NUMBER, Integer.class);
        Assert.assertNotNull(result);
        Assert.assertTrue((result.get()) > 0);
    }
}
