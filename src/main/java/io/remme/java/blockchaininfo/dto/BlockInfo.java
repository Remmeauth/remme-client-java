package io.remme.java.blockchaininfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockInfo {
    @JsonProperty(value = "block_number")
    private Integer blockNum;
    private Long timestamp;
    @JsonProperty(value = "previous_header_signature")
    private String previousHeaderSignature;
    @JsonProperty(value = "header_signature")
    private String headerSignature;
    @JsonProperty(value = "signer_public_key")
    private String signerPublicKey;
}
