package io.remme.java.api;

import io.remme.java.enums.RemmeMethod;

import java.util.Map;
import java.util.concurrent.Future;

/**
    RemmeApi interface to work with JSON-RPC
 */
public interface IRemmeApi {
    boolean isSslMode();
    String getNodeAddress();
    <T> Future<T> sendRequest(RemmeMethod method);
    <T> Future<T> sendRequest(RemmeMethod method, Map<String, Object> params);
}
