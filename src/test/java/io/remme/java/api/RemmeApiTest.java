package io.remme.java.api;

import io.remme.java.enums.RemmeMethod;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemmeApiTest {

    @Test
    public void testSendRequestWithParams() {
        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io", 8080, false);
        Map<String, Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("limit", 50);
        Object result = remmeApi.sendRequest(RemmeMethod.BLOCK_INFO, params);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof List);
        Assert.assertEquals(50, ((List)result).size());
    }

    @Test
    public void testSendRequestWithoutParams() {
        RemmeApi remmeApi = new RemmeApi("node-genesis-testnet-dev.remme.io", 8080, false);
        Object result = remmeApi.sendRequest(RemmeMethod.BLOCK_NUMBER);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof Integer);
        Assert.assertTrue(((Integer)result) > 0);
    }
}
