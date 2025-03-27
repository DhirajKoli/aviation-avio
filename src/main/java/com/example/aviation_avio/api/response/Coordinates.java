package com.example.aviation_avio.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Coordinates{
    private double latitude;
    private double longitude;
}
