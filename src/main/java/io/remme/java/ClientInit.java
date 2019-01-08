package io.remme.java;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Class to initialize RemmeClient
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientInit {
    //network config
    /**
     * REMME node address
     */
    private String nodeAddress = "localhost";
    /**
     * REMME node port
     */
    private Integer nodePort = 8080;
    /**
     * security protocol https or https
     */
    private boolean sslMode = false;
    //private key in HEX
    /**
     * Private Key in HEX for account initialization
     */
    private String privateKeyHex;
}
