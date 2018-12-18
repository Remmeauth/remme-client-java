package io.remme.java.websocket;

@FunctionalInterface
public interface SocketEventListener {
   void callback(Object error, Object result);
}
