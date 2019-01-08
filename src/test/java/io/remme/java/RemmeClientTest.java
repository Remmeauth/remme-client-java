package io.remme.java;

import org.junit.Assert;
import org.junit.Test;

public class RemmeClientTest {
    @Test
    public void testClient() {
        RemmeClient client = new RemmeClient();
        Assert.assertNotNull(client.getAccount().getAddress());
    }
}
