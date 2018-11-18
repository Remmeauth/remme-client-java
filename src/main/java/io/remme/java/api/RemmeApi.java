package io.remme.java.api;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import com.github.arteam.simplejsonrpc.client.Transport;
import com.github.arteam.simplejsonrpc.client.builder.RequestBuilder;
import com.github.arteam.simplejsonrpc.client.exception.JsonRpcException;
import com.google.common.net.MediaType;
import io.remme.java.enums.RemmeMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * Implementation of {@link IRemmeApi} to work with JSON-RPC node
 */
@Data
@NoArgsConstructor
public class RemmeApi implements IRemmeApi {
    private String nodeAddress;
    private Integer nodePort;
    private boolean sslMode;
    private static JsonRpcClient jsonRpcClient;

    /**
     * RemmeApi constructor to initiate node configuration
     *
     * @param nodeAddress hostname or IP-address of Remme node
     * @param nodePort port of Remme node
     * @param sslMode set this flag to 'true' if you want to use SSL mode (https://)
     */
    public RemmeApi(String nodeAddress, Integer nodePort, boolean sslMode) {
        Asserts.check(nodeAddress != null, "nodeAddress should not be null!");
        Asserts.check(nodePort != null, "nodePort should not be null!");
        this.nodeAddress = nodeAddress;
        this.nodePort = nodePort;
        this.sslMode = sslMode;
    }

    public String getNodeAddress() {
        return nodeAddress + ":" + nodePort;
    }

    /**
     * Use this method to send JSON-RPC request (specification 2.0) to Remme node
     *
     * @param method specifies method on Remme node
     * @return response from Remme node in 'result' field or ErrorMessage from 'error' field in case of error result
     * @see RemmeMethod
     * @see com.github.arteam.simplejsonrpc.core.domain.ErrorMessage
     */
    public Object sendRequest(RemmeMethod method) {
        return sendRequest(method, null);
    }

    /**
     * Use this method to send JSON-RPC request (specification 2.0) to Remme node with some input parameters
     *
     * @param method specifies method on Remme node
     * @param params map with key-value parameters which will be added to 'params' field during request
     * @return response from Remme node in 'result' field or ErrorMessage from 'error' field in case of error result
     * @see RemmeMethod
     * @see com.github.arteam.simplejsonrpc.core.domain.ErrorMessage
     */
    public Object sendRequest(RemmeMethod method, Map<String, Object> params) {
        try {
            RequestBuilder<Object> requestBuilder = getRequestBuilder(method);
            if (params != null && !params.isEmpty()) {
                for (String name : params.keySet()) {
                    requestBuilder = requestBuilder.param(name, params.get(name));
                }
            }
            return requestBuilder.execute();
        } catch (JsonRpcException e) {
            return e.getErrorMessage();
        }
    }

    /**
     * @return complete request URL based on configuration parameters
     */
    public String getURLForRequest() {
        return (sslMode ? "https://" : "http://") + getNodeAddress();
    }

    private RequestBuilder<Object> getRequestBuilder(RemmeMethod method) {
        initClient();
        return jsonRpcClient.createRequest()
                .method(method.getMethodName())
                .id(Math.round(Math.random() * 100));
    }

    private void initClient() {
        jsonRpcClient = new JsonRpcClient(new Transport() {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            @NotNull
            public String pass(@NotNull String request) throws IOException {
                HttpPost post = new HttpPost(getURLForRequest());
                post.setEntity(new StringEntity(request, Charsets.toCharset("UTF-8")));
                post.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
                try (CloseableHttpResponse httpResponse = httpClient.execute(post)) {
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        return EntityUtils.toString(httpResponse.getEntity(), Charsets.toCharset("UTF-8"));
                    } else {
                        throw new HttpException("Please check if your node running at " + getURLForRequest());
                    }
                } catch (HttpException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
