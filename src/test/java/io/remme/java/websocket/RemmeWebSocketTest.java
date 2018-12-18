package io.remme.java.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.remme.java.websocket.dto.RemmeRequestParams;
import io.remme.java.websocket.enums.RemmeEvents;
import org.junit.Test;

public class RemmeWebSocketTest {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    @Test
    public void testRemmeWebSocket() {
        RemmeWebSocket ws = new RemmeWebSocket("138.197.204.63:8080", false, new RemmeRequestParams(RemmeEvents.BLOCKS,
                null, null, null));
        ws.connectToWebSocket((error, result) -> {
            try {
                if (error != null) {
                    System.out.println(MAPPER.writeValueAsString(error));
                    return;
                }
                System.out.println(MAPPER.writeValueAsString(result));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        while (true) {

        }

    }
}
