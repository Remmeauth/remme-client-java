package io.remme.java.blockchaininfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkStatus {
    @JsonProperty(value = "is_synced")
    private Boolean isSynced;
    @JsonProperty(value = "peer_count")
    private Integer peerCount;
}
