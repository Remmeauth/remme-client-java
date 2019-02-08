package io.remme.java.blockchaininfo.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData<T extends BaseHeader> {
    private T header;
    private String header_signature;
}
