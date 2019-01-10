package io.remme.java.blockchaininfo.dto.blocks;

import io.remme.java.blockchaininfo.dto.responses.BaseHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockHeader extends BaseHeader {
    private String[] batch_ids;
    private String block_num;
    private String consensus;
    private String previous_block_id;
    private String state_root_hash;
}
