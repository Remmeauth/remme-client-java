package io.remme.java.api;

import io.remme.java.enums.RemmeMethod;

import java.util.Map;

/**
    RemmeApi interface to work with JSON-RPC
 */
public interface IRemmeApi {
    boolean isSslMode();
    String getNodeAddress();
    Object sendRequest(RemmeMethod method);
    Object sendRequest(RemmeMethod method, Map<String, Object> params);
}
