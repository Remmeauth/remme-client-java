package io.remme.java.websocket.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidTransactions {
    private String transaction_id;
    private String message;
    private String extended_data;
}
