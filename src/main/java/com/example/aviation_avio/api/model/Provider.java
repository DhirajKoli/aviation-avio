package com.example.aviation_avio.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Provider {

    private String aid;
    private String name;
    private String website;
    private String phone;
    private String email;
    private Country country;
    private String category;
    private String parent;
    private boolean archived;
}
