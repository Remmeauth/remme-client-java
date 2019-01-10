package io.remme.java.blockchaininfo.dto.transactions;

import io.remme.java.blockchaininfo.dto.responses.BaseResponse;
import lombok.Data;

@Data
public class TransactionList extends BaseResponse<TransactionData[]> {
}
