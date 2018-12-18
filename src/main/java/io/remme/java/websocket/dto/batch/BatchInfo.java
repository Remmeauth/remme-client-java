package io.remme.java.websocket.dto.batch;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchInfo {
    private BatchStatus status;
    private String batch_id;
    private InvalidTransactions[] invalid_transactions;
}
