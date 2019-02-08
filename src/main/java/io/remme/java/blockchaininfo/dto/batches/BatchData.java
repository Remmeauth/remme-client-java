package io.remme.java.blockchaininfo.dto.batches;

import io.remme.java.blockchaininfo.dto.responses.BaseData;
import io.remme.java.blockchaininfo.dto.transactions.TransactionData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchData extends BaseData<BatchHeader> {
    private TransactionData[] transactions;
    private Boolean trace;
}
