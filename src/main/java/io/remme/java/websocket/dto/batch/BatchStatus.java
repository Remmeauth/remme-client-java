package io.remme.java.websocket.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BatchStatus {
    UNKNOWN("UNKNOWN"),
    PENDING("PENDING"),
    INVALID("INVALID"),
    COMMITTED("COMMITTED");

    private String status;
}
