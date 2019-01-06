package io.remme.java.blockchaininfo.dto.state;

import io.remme.java.blockchaininfo.dto.responses.BaseResponse;
import io.remme.java.blockchaininfo.dto.responses.Paging;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State extends BaseResponse<String> {
    private String address;

    @Builder
    public State(String data, String head, String link, Paging paging, String address) {
        super(data, head, link, paging);
        this.address = address;
    }
}
