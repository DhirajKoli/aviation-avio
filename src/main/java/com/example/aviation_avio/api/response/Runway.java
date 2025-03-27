package com.example.aviation_avio.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Runway {
    public String aid;
    public boolean helipad;
    public String identifier;
    public double magneticBearing;
    public double trueBearing;
    public double thresholdElevation;
    public double width;
    public String surface;
    public boolean endLights;
    public boolean centerLights;
    public int centerLightsSpacing;
    public boolean touchDownZoneLights;
    public boolean instrumental;
    public boolean precision;
    public boolean separate;
    public Coordinates thresholdCoordinates;
    public String parent;
    public boolean archived;
}
