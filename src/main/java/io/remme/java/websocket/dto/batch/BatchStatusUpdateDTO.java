package io.remme.java.websocket.dto.batch;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStatusUpdateDTO {
    private String id;
    private String type;
    private Data data;
}