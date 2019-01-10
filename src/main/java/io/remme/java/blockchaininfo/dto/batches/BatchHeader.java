package io.remme.java.blockchaininfo.dto.batches;

import io.remme.java.blockchaininfo.dto.responses.BaseHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchHeader extends BaseHeader {
    private String[] transaction_ids;
}
