package io.remme.java.websocketevents;

import io.remme.java.websocket.IRemmeWebSocket;
import io.remme.java.websocket.SocketEventListener;
import io.remme.java.websocket.dto.RemmeRequestParams;

public interface IRemmeWebSocketEvents extends IRemmeWebSocket {
    void subscribe(RemmeRequestParams data, SocketEventListener listener);
    void unsubscribe();
}
