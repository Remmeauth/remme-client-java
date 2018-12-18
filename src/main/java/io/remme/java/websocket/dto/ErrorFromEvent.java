package io.remme.java.websocket.dto;

import io.remme.java.websocket.dto.batch.BatchInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorFromEvent {
    public String transactionId;
    public String message;

    public ErrorFromEvent(BatchInfoDTO batchInfo) {
        transactionId = batchInfo.getId();
        message = batchInfo.getError();
    }
}
