package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.remme.java.websocket.enums.RemmeMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonRpcRequest {
    private String jsonrpc = "2.0";
    private RemmeMethods method;
    private RemmeRequestParams params;
    private String id = Hex.encodeHexString(DigestUtils.sha256(String.valueOf(Math.random() * 1000)));

    public JsonRpcRequest(RemmeMethods method, RemmeRequestParams params) {
        this.method = method;
        this.params = params;
    }
}
