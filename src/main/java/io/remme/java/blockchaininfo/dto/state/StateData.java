package io.remme.java.blockchaininfo.dto.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateData {
    private String address;
    private String data;
    private Object protobuf;
    private String addressType;
}
