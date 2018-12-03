package io.remme.java.api;

import io.remme.java.enums.RemmeMethod;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RemmeApiTest {

    @Test
    public void testSendRequestWithParams() throws ExecutionException, InterruptedException {
        RemmeApi<List> remmeApi = new RemmeApi<>("node-genesis-testnet-dev.remme.io", 8080, false);
        Map<String, Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("limit", 50);
        Future<List> result = remmeApi.sendRequest(RemmeMethod.BLOCK_INFO, params);
        Assert.assertNotNull(result);
        Assert.assertEquals(50, (result.get()).size());
    }

    @Test
    public void testSendRequestWithoutParams() throws ExecutionException, InterruptedException {
        RemmeApi<Integer> remmeApi = new RemmeApi<>("node-genesis-testnet-dev.remme.io", 8080, false);
        Future<Integer> result = remmeApi.sendRequest(RemmeMethod.BLOCK_NUMBER);
        Assert.assertNotNull(result);
        Assert.assertTrue((result.get()) > 0);
    }
}
