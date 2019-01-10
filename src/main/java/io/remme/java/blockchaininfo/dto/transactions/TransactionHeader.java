package io.remme.java.blockchaininfo.dto.transactions;

import io.remme.java.blockchaininfo.dto.responses.BaseHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHeader extends BaseHeader {
    private String batcher_public_key;
    private String[] dependencies;
    private String family_name;
    private String family_version;
    private String[] inputs;
    private String nonce;
    private String[] outputs;
    private String payload_sha512;
}





