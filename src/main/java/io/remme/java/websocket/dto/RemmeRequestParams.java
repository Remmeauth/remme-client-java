package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private RemmeEvents event_type;
    private String id;
    private String from_block;
    private String address;
}
