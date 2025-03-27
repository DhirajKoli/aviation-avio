package com.example.aviation_avio.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class PageResponse<T> {
    private Page page;
    private List<T> content;
}
