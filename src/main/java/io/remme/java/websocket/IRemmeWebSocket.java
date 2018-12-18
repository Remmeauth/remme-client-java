package io.remme.java.websocket;

public interface IRemmeWebSocket {
    void connectToWebSocket(SocketEventListener listener);
    void closeWebSocket();
}
