package io.remme.java.websocketevents;

import io.remme.java.api.NetworkConfig;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.websocket.RemmeWebSocket;
import io.remme.java.websocket.SocketEventListener;
import io.remme.java.websocket.dto.RemmeRequestParams;

/**
 * Class for subscribing to events from WebSocket.
 * Available types for subscribing is covered in
 * <a traget="_top" href="https://docs.remme.io/remme-core/docs/remme-ws-events.html#registered-events">Registered Events</a>
 */
public class RemmeWebSocketEvents extends RemmeWebSocket implements IRemmeWebSocketEvents {
    /**
     * Implementation of RemmeWebSocketsEvents;
     *
     * <pre>
     * RemmeWebSocketEvents remmeEvents = new RemmeWebSocketsEvents(
     *     new NetworkConfig("localhost:8080", false));
     * </pre>
     * @param networkConfig network configuration of REMME node
     */
    public RemmeWebSocketEvents(NetworkConfig networkConfig) {
        super(networkConfig, null);
    }

    /**
     * Subscribing to events from WebSocket.
     * Available types for subscribing is covered in
     * <a traget="_top" href="https://docs.remme.io/remme-core/docs/remme-ws-events.html#registered-events">Registered Events</a>
     *
     * You can subscribe on events about Swap
     * <pre>
     * remmeEvents.subscribe(
     *      RemmeRequestParams.builder().event_type(RemmeEvents.ATOMIC_SWAP).build()
     * , (err, res) {@code ->} {
     *      System.out.println("new event error:" + MAPPER.writeValueAsString(err));
     *      System.out.println("new event result:" + MAPPER.writeValueAsString(res));
     * });
     * </pre>
     * You can subscribe on events about Batch
     * <pre>
     * remmeEvents.subscribe({
     *      RemmeRequestParams.builder().event_type(RemmeEvents.BATCH).build()
     * , (err, res) {@code ->} {
     *      System.out.println("new event error:" + MAPPER.writeValueAsString(err));
     *      System.out.println("new event result:" + MAPPER.writeValueAsString(res));
     * });
     * </pre>
     */
    public void subscribe(RemmeRequestParams data, SocketEventListener listener) {
        if (this.client != null) {
            super.closeWebSocket();
        }
        this.data = data;
        super.connectToWebSocket(listener);
    }

    /**
     * Unsubscribing from events.
     * Regardless of how many events you subscribed to, you always unsubscribe from all
     *
     * <pre>
     * remmeEvents.unsubscribe();
     * </pre>
     */
    public void unsubscribe() {
        if (this.client != null) {
            super.closeWebSocket();
        } else {
            throw new RemmeValidationException("WebSocket is not running");
        }
    }
}
