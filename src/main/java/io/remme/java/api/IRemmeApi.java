package io.remme.java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.remme.java.enums.RemmeMethod;

import java.util.concurrent.Future;

/**
    RemmeApi interface to work with JSON-RPC
 */
public interface IRemmeApi {
    ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Get network configuration for REMChain node
     *
     * @return {@link NetworkConfig}
     */
    NetworkConfig getNetworkConfig();
    <T> Future<T> sendRequest(RemmeMethod method, Class<T> clazz);
    <T> Future<T> sendRequest(RemmeMethod method, Object input, Class<T> clazz);
}
