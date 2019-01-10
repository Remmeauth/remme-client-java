package io.remme.java.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetworkConfig {
    private String nodeAddress = "localhost:8080";
    private boolean sslMode = false;
}
