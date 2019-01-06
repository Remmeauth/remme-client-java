package io.remme.java.blockchaininfo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateQueryRequest extends BaseQueryRequest {
    private String address;

    public StateQueryRequest(StateQuery query) {
        super(query);
        this.address = query.getAddress();
    }
}
