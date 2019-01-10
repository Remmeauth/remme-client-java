package io.remme.java.blockchaininfo.dto.transactions;

import io.remme.java.blockchaininfo.dto.responses.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData extends BaseData<TransactionHeader> {
    private String payload;
    private Object transactionProtobuf;
    private Object protobuf;
    private String transactionType;
}
