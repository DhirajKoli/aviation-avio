package com.example.aviation_avio.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Authority {
    private String aid;
    private String name;
    private String type;
    private String parent;
    private String kind;
}
