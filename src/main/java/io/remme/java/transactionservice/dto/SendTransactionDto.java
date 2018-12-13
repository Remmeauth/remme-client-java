package io.remme.java.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for send transaction
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTransactionDto {
    private String data;
}
