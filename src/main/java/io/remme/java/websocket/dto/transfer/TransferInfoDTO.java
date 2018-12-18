package io.remme.java.websocket.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferInfoDTO {
    private TransferClientInfo from;
    private TransferClientInfo to;
}
