package io.remme.java.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating transaction:
 * Documentation for building transactions
 * <a target="_top" href="https://sawtooth.hyperledger.org/docs/core/releases/latest/_autogen/sdk_submit_tutorial_js.html#building-the-transaction">
 *     Building the transaction</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransactionDto {
    private String familyName;
    private String familyVersion;
    private String[] inputs;
    private String[] outputs;
    private byte[] payloadBytes;
}
