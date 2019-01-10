package io.remme.java.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arteam.simplejsonrpc.core.domain.ErrorMessage;
import io.remme.java.api.NetworkConfig;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.error.RemmeSocketException;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.websocket.dto.*;
import io.remme.java.atomicswap.dto.SwapInfoDTO;
import io.remme.java.websocket.dto.batch.BatchInfoDTO;
import io.remme.java.websocket.dto.batch.BatchStatus;
import io.remme.java.websocket.dto.block.BlockInfoDTO;
import io.remme.java.websocket.dto.transfer.TransferInfoDTO;
import io.remme.java.websocket.enums.RemmeEvents;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class RemmeWebSocket implements IRemmeWebSocket {
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    private NetworkConfig networkConfig;
    private static final Map<RemmeEvents, Class<?>> EVENT_MAP;

    static {
        EVENT_MAP = new HashMap<>();
        EVENT_MAP.put(RemmeEvents.ATOMIC_SWAP, SwapInfoDTO.class);
        EVENT_MAP.put(RemmeEvents.BATCH, BatchInfoDTO.class);
        EVENT_MAP.put(RemmeEvents.BLOCKS, BlockInfoDTO.class);
        EVENT_MAP.put(RemmeEvents.TRANSFER, TransferInfoDTO.class);
    }

    protected WebSocketClient client;
    protected RemmeRequestParams data;

    public RemmeWebSocket(String nodeAddress, boolean sslMode, RemmeRequestParams params) {
        this(new NetworkConfig(nodeAddress, sslMode), params);
    }

    public RemmeWebSocket(NetworkConfig networkConfig, RemmeRequestParams params) {
        this.networkConfig = networkConfig;
        this.data = params;
    }

    private String getSubscribeUrl() {
        return (this.networkConfig.isSslMode() ? "wss://" : "ws://") + this.networkConfig.getNodeAddress() + "/";
    }

    private String getSocketQuery() {
        return getSocketQuery(true);
    }

    private String getSocketQuery(boolean subscribe) {
        if (this.data == null) {
            throw new Error("Data for subscribe was not provided");
        }
        RemmeMethod method = subscribe ? RemmeMethod.SUBSCRIBE : RemmeMethod.UNSUBSCRIBE;
        try {
            return MAPPER.writeValueAsString(new JsonRpcRequest(method, this.data));
        } catch (JsonProcessingException e) {
            throw new RemmeSocketException(e);
        }
    }

    private void sendAnError(Object error, SocketEventListener listener) {
        this.closeWebSocket();
        listener.callback(error, null);
    }

    @Override
    public void connectToWebSocket(SocketEventListener listener) {
        try {
            if (client != null && client.isOpen()) {
                client.close();
            }
            this.client = new WebSocketClient(new URI(getSubscribeUrl())) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    send(getSocketQuery());
                }

                @Override
                public void onMessage(String message) {
                    try {
                        JsonRpcResponse response = MAPPER.readValue(message, JsonRpcResponse.class);

                        if (response.getError() != null) {
                            sendAnError(response.getError(), listener);
                            return;
                        }
                        if (response.getResult() != null && !(response.getResult() instanceof String)) {
                            JsonRpcResult result = MAPPER.convertValue(response.getResult(), JsonRpcResult.class);
                            if (RemmeEvents.BATCH.equals(result.getEvent_type())) {
                                BatchInfoDTO batchInfo = MAPPER.convertValue(result.getAttributes(),
                                        BatchInfoDTO.class);
                                if (batchInfo.getStatus().equals(BatchStatus.INVALID)) {
                                    sendAnError(new ErrorFromEvent(batchInfo), listener);
                                    return;
                                }
                            }
                            listener.callback(null, MAPPER.convertValue(result.getAttributes(), EVENT_MAP.get(result.getEvent_type())));
                        }
                    } catch (Exception e) {
                        throw new RemmeSocketException(e);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    if (code != 1000) {
                        listener.callback(new ErrorMessage(code, reason), null);
                    }
                }

                @Override
                public void onError(Exception e) {
                    throw new RemmeSocketException(e);
                }
            };
            client.connect();
        } catch (URISyntaxException e) {
            throw new RemmeSocketException(e);
        }
    }

    @Override
    public void closeWebSocket() {
        if (this.client == null || this.client.isClosed()) {
            throw new RemmeValidationException("WebSocket is not running");
        }
        if (WebSocket.READYSTATE.OPEN.equals(this.client.getReadyState())) {
            this.client.send(this.getSocketQuery(false));
        }
        this.client.close();
        this.client = null;
    }
}
