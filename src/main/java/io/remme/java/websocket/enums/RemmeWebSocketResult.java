package io.remme.java.websocket.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RemmeWebSocketResult {
    SUBSCRIBED("SUBSCRIBED"),
    UNSUBSCRIBED("UNSUBSCRIBED");

    private String result;
}
