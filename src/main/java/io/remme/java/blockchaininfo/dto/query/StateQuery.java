package io.remme.java.blockchaininfo.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.remme.java.error.RemmeValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateQuery extends BaseQuery {
    private String address;

    @Builder
    public StateQuery(String head, Integer limit, Object start, Object reverse, String address) {
        super(head, start, limit, reverse);
        this.address = address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        if (address != null) {
            if (!address.matches("^[a-f0-9]{70}$")) {
                throw new RemmeValidationException("Parameter 'address' is not valid");
            } else {
                this.address = address;
            }
        }
    }
}
