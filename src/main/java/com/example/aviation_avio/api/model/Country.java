package com.example.aviation_avio.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Country{
    private String iso2;
    private String iso3;
    private int isoNumeric;
    private String name;
    private String officialName;
}
