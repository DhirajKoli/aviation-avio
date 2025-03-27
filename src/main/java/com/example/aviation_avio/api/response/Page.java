package com.example.aviation_avio.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Page {
    private int number;
    private int size;
    private long totalPages;
    private int totalResults;
}
