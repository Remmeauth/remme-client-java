package io.remme.java.blockchaininfo.dto.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.remme.java.enums.Patterns;
import io.remme.java.enums.RemmeFamilyName;
import io.remme.java.error.RemmeValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseQueryRequest {
    private String head;
    private String start;
    private Integer limit;
    private String reverse;
    @JsonProperty("family_name")
    private RemmeFamilyName familyName;

    public BaseQueryRequest(BaseQuery query) {
        this.head = query.getHead();
        setStart(query.getStart());
        this.limit = query.getLimit();
        setReverse(query.getReverse());
        this.familyName = query.getFamilyName();
    }

    @JsonProperty(value = "reverse")
    public void setReverse(Object reverse) {
        if (reverse != null) {
            if (reverse instanceof Boolean) {
                this.reverse = String.valueOf(reverse);
            }
            if (reverse instanceof String) {
                this.reverse = (String) reverse;
            }
        }
    }

    @JsonProperty(value = "head")
    public void setHead(String head) {
        if (head != null && !head.matches("[a-f0-9]{128}")) {
            throw new RemmeValidationException("Parameter 'head' is not valid");
        } else {
            this.head = head;
        }
    }

    @JsonProperty(value = "start")
    public void setStart(Object start) {
        if (start != null) {
            if (start instanceof String && (
                    ((String) start).matches("^0x[a-f0-9]{16}$") || ((String) start).matches("^[a-f0-9]{128}$")
                            || ((String) start).matches(Patterns.ADDRESS.getPattern()))) {
                this.start = (String) start;
            } else if (start instanceof Integer) {
                this.start = String.valueOf(start);
            } else {
                throw new RemmeValidationException("Parameter 'start' is not valid");
            }
        }
    }
}
