package io.remme.java.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.remme.java.websocket.dto.RemmeRequestParams;
import io.remme.java.websocket.enums.RemmeEvents;
import org.junit.Assert;
import org.junit.Test;

public class RemmeWebSocketTest {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static boolean success = true;
    @Test
    public void testRemmeWebSocket() throws InterruptedException {
        RemmeWebSocket ws = new RemmeWebSocket("node-1-testnet-dev.remme.io:8080", false, new RemmeRequestParams(RemmeEvents.BLOCKS,
                null, null, null));
        ws.connectToWebSocket((error, result) -> {
            try {
                if (error != null) {
                    System.out.println(MAPPER.writeValueAsString(error));
                    success = false;
                    return;
                }
                System.out.println(MAPPER.writeValueAsString(result));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(30000);
        Assert.assertTrue(success);
    }
}
