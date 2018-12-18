package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.remme.java.enums.RemmeMethod;
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
    private RemmeMethod method;
    private RemmeRequestParams params;
    private String id = Hex.encodeHexString(DigestUtils.sha256(String.valueOf(Math.random() * 1000)));

    public JsonRpcRequest(RemmeMethod method, RemmeRequestParams params) {
        this.method = method;
        this.params = params;
    }
}
