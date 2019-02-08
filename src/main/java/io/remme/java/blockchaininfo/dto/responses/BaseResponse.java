package io.remme.java.blockchaininfo.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    protected T data;
    protected String head;
    protected String link;
    protected Paging paging;
}