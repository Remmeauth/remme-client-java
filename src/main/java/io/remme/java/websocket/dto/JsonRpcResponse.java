package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.arteam.simplejsonrpc.core.domain.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRpcResponse {
    private String jsonrpc;
    private Object result;
    private ErrorMessage error;
    private String id;
}
