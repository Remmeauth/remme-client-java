package io.remme.java.api;

import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import com.github.arteam.simplejsonrpc.client.Transport;
import com.github.arteam.simplejsonrpc.client.builder.RequestBuilder;
import com.google.common.net.MediaType;
import io.remme.java.enums.RemmeMethod;
import io.remme.java.utils.RemmeExecutorService;
import lombok.Builder;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Main class that send requests to our REMME protocol;
 * <br>Check JSON-RPC API specification:<br>
 * <a target="_top" href="https://remmeio.atlassian.net/wiki/spaces/WikiREMME/pages/292814862/RPC+API+specification">RPC API specification</a>.
 * <br>Implementation of {@link IRemmeApi} to work with JSON-RPC node
 */
@Data
@NoArgsConstructor
@Builder
public class RemmeApi implements IRemmeApi {
    private String nodeAddress = "localhost";
    private Integer nodePort = 8080;
    private boolean sslMode = false;

    /**
     * RemmeApi constructor to initiate node configuration. All parameters are optional
     *<p>
     * By default params for constructor are:<br>
     * <pre>
     * nodeAddress: "localhost"
     * nodePort: 8080
     * sslMode: false
     * </pre>
     * <p>Example</p>
     * Implementation with all parameters:
     * <pre>
     *     {@code RemmeApi<Object> remmeApi = new RemmeApi<>("localhost", 8080, false)};
     * </pre>
     * Implementation with one parameter:
     * <pre>
     *     {@code RemmeApi<Object> remmeApi = RemmeApi<Object>.nodeAddress("localhost").build();}
     * </pre>
     * Implementation without parameters:
     * <pre>
     *     {@code RemmeApi<Object> remmeApi = new RemmeApi<>()};
     * </pre>
     *
     * @param nodeAddress hostname or IP-address of Remme node
     * @param nodePort    port of Remme node
     * @param sslMode     set this flag to 'true' if you want to use SSL mode (https://)
     */
    public RemmeApi(String nodeAddress, Integer nodePort, boolean sslMode) {
        Asserts.check(nodeAddress != null, "nodeAddress should not be null!");
        Asserts.check(nodePort != null, "nodePort should not be null!");
        this.nodeAddress = nodeAddress;
        this.nodePort = nodePort;
        this.sslMode = sslMode;
    }

    /**
     * Get node address as concatenation of host and port
     *
     * @return concatenation of host and port
     */
    public String getNodeAddress() {
        return nodeAddress + ":" + nodePort;
    }

    /**
     * Use this method to send JSON-RPC request (specification 2.0) to Remme node
     *
     * <pre>
     *      RemmeApi remmeApi = new RemmeApi("localhost", 8080, false);
     *
     *      {@code Future<Integer> result = remmeApi.sendRequest(RemmeMethod.BLOCK_NUMBER);}
     *       System.out.println(result.get());
     * </pre>
     *
     * @param method specifies method on Remme node
     * @return response from Remme node in 'result' field or ErrorMessage inside JsonRpcException from 'error' field
     * in case of error result
     * @see RemmeMethod
     * @see com.github.arteam.simplejsonrpc.core.domain.ErrorMessage
     * @see com.github.arteam.simplejsonrpc.client.exception.JsonRpcException
     */
    @Override
    public <T> Future<T> sendRequest(RemmeMethod method, Class<T> clazz) {
        return sendRequest(method, null, clazz);
    }

    /**
     * Use this method to send JSON-RPC request (specification 2.0) to Remme node with some input parameters
     *
     * <pre>
     *      RemmeApi remmeApi = new RemmeApi("localhost", 8080, false);
     *
     *      {@code Map<String, Object> params = new HashMap<>();}
     *         params.put("start", 0);
     *         params.put("limit", 50);
     *      {@code Future<List> result = remmeApi.sendRequest(RemmeMethod.BLOCK_INFO, params);}
     *       System.out.println(result.get().size());
     * </pre>
     *
     * @param method specifies method on Remme node
     * @param input map with key-value parameters which will be added to 'params' field during request
     * @return response from Remme node in 'result' field or ErrorMessage inside JsonRpcException from 'error' field
     * in case of error result
     * @see RemmeMethod
     * @see com.github.arteam.simplejsonrpc.core.domain.ErrorMessage
     * @see com.github.arteam.simplejsonrpc.client.exception.JsonRpcException
     */
    @Override
    public <T> Future<T> sendRequest(RemmeMethod method, Object input, Class<T> clazz) {
        Map<String, Object> params = MAPPER.convertValue(input, Map.class);
        ExecutorService es = RemmeExecutorService.getInstance();
        RequestBuilder<Object> requestBuilder = getRequestBuilder(method);
        if (params != null && !params.isEmpty()) {
            for (String name : params.keySet()) {
                requestBuilder = requestBuilder.param(name, params.get(name));
            }
        }
        RequestBuilder<Object> finalRequestBuilder = requestBuilder;
        return es.submit(() -> MAPPER.convertValue(finalRequestBuilder.execute(), clazz));
    }

    /**
     * @return complete request URL based on configuration parameters
     */
    public String getURLForRequest() {
        return (sslMode ? "https://" : "http://") + getNodeAddress();
    }

    private RequestBuilder<Object> getRequestBuilder(RemmeMethod method) {
        return initClient().createRequest()
                .method(method.getMethodName())
                .id(Math.round(Math.random() * 100));
    }

    private JsonRpcClient initClient() {
        return new JsonRpcClient(new Transport() {
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
