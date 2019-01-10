package io.remme.java;

import io.remme.java.api.NetworkConfig;
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
     * REMME node network configuration
     */
    private NetworkConfig networkConfig;
    //private key in HEX
    /**
     * Private Key in HEX for account initialization
     */
    private String privateKeyHex;
}
