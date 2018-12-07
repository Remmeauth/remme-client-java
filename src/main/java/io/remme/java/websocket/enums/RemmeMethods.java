package io.remme.java.websocket.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RemmeMethods {
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private String method;

    @JsonValue
    public String getMethod() {
        return method;
    }
}
