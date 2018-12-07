package io.remme.java.websocket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.remme.java.websocket.enums.RemmeEvents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemmeRequestParams {
    public RemmeEvents event_type;
    public String id;
    public String from_block;
    public String address;
}
