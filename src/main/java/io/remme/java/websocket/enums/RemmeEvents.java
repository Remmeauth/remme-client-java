package io.remme.java.websocket.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum RemmeEvents {
    BLOCKS("blocks"),
    BATCH("batch"),
    TRANSFER("transfer"),
    ATOMIC_SWAP("atomic_swap");

    private String event;

    @JsonValue
    public String getEvent() {
        return event;
    }

    @JsonCreator
    public static RemmeEvents forValue(String v) {
        return Stream.of(RemmeEvents.values()).filter(val -> val.getEvent().equalsIgnoreCase(v)).findFirst().orElse(null);
    }
}
