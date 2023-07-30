package com.shukalovich.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserFilter {

    private final Byte LIMIT = 5;
    private final Byte OFFSET = 0;

    private Integer limit;
    private Integer page;

    public Integer getLimit() {
        return limit == null ? LIMIT : limit;
    }

    public Integer getOffset() {
        return page == null ? OFFSET : limit * (page - 1);
    }
}
