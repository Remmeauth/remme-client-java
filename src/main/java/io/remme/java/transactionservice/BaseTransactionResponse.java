package io.remme.java.transactionservice;

import io.remme.java.enums.Patterns;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.websocket.RemmeWebSocket;
import io.remme.java.websocket.dto.RemmeRequestParams;
import io.remme.java.websocket.enums.RemmeEvents;
import lombok.Getter;

/**
 * Main class for response on transaction request, which contain identifier of batch and communication with WebSockets.
 */
@Getter
public class BaseTransactionResponse extends RemmeWebSocket {
    /**
     * Identifier of batch that contains sending transaction
     */
    private String batchId;

    /**
     * Get address of node, ssl mode, and identifier of batch.
     * Then implement RemmeWebSocket class and provide data to it.
     *
     * @param nodeAddress hostname or IP of the node
     * @param sslMode     define http or https protocol
     * @param batchId     identifier of batch that contains sending transaction
     */
    public BaseTransactionResponse(String nodeAddress, boolean sslMode, String batchId) {
        super(nodeAddress, sslMode, new RemmeRequestParams(RemmeEvents.BATCH, batchId, null, null));
        setBatchId(batchId);
    }

    /**
     * Set batch id. When you provide new batch id to this object, program check web socket connection,
     * if connection is open, program close it and then provide new batch id.
     * And you can connect to web socket again with new batch id.
     *
     * @param value batchId
     */
    public void setBatchId(String value) {
        if (!value.matches(Patterns.HEADER_SIGNATURE.getPattern())) {
            throw new RemmeValidationException("Given batch id is invalid");
        }
        if (this.client != null && this.client.isOpen()) {
            super.closeWebSocket();
        }
        this.batchId = value;
        this.data.setId(value);
    }
}
