package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.remme.java.enums.Patterns;
import io.remme.java.error.RemmeValidationException;
import io.remme.java.websocket.enums.RemmeEvents;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemmeRequestParams {
    @JsonProperty("event_type")
    private RemmeEvents events;
    private String id;
    @JsonProperty("from_block")
    private String lastKnownBlockId;
    private String address;

    public void setId(String id) {
        this.id = id;
        if (events != null) {
            validateId();
        }
    }

    public void setEvents(RemmeEvents events) {
        this.events = events;
        validateId();
        validateAddress();
    }

    public void setAddress(String address) {
        this.address = address;
        if (events != null) {
            validateAddress();
        }
    }

    public void setLastKnownBlockId(String lastKnownBlockId) {
        if (lastKnownBlockId != null && !lastKnownBlockId.matches(Patterns.HEADER_SIGNATURE.getPattern())) {
            throw new RemmeValidationException("'lastKnownBlockId' is not correct");
        }
        this.lastKnownBlockId = lastKnownBlockId;
    }

    private void validateId(){
        if (id != null && (RemmeEvents.BATCH.equals(events) || RemmeEvents.ATOMIC_SWAP.equals(events)) &&
                !this.id.matches(Patterns.HEADER_SIGNATURE.getPattern())) {
            throw new RemmeValidationException("'id' is not correct");
        }
    }

    private void validateAddress() {
        if (address != null && !this.address.matches(Patterns.ADDRESS.getPattern())) {
            throw new RemmeValidationException("'address' is not correct");
        }
    }

}