package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.remme.java.websocket.enums.RemmeEvents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonRpcResult {
    RemmeEvents event_type;
    String attributes; //IAtomicSwap | IBatch | IBlock | ITransfer;
}
