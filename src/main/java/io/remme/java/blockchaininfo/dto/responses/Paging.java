package io.remme.java.blockchaininfo.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {
    private Integer limit;
    private Object start;

    @JsonProperty(value = "start")
    public void setStart(Object start) {
        if (start instanceof String && ((String) start).contains("0x")) {
            this.start = new BigInteger(((String) start).replace("0x", ""), 16).intValue();
        } else {
            this.start = start;
        }
    }
}