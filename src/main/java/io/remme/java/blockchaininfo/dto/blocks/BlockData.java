package io.remme.java.blockchaininfo.dto.blocks;

import io.remme.java.blockchaininfo.dto.batches.BatchData;
import io.remme.java.blockchaininfo.dto.responses.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockData extends BaseData<BlockHeader> {
    private BatchData[] batches;
}
