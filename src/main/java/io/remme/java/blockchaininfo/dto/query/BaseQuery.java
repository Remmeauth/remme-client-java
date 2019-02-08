package io.remme.java.blockchaininfo.dto.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.remme.java.enums.RemmeFamilyName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseQuery {
    private String head;
    private Object start;
    private Integer limit;
    private Object reverse;
    @JsonProperty("family_name")
    private RemmeFamilyName familyName;
}
