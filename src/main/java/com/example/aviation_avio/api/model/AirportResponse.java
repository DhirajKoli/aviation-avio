package com.example.aviation_avio.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class AirportResponse {

    private String aid;
    private String name;
    private String servedCity;
    private double elevation;
    private Coordinates coordinates;
    private double variation;
    private Country country;
    private String timeZone;
    private String type;
    private String operator;
    private ArrayList<String> accessibleFor;
    private boolean ifr;
    private String nonScheduledPermission;
    private String nonScheduledPermissionNotes;
    private boolean mandatoryHandling;
    private boolean mandatoryQualification;
    private double weightLimit;
    private double wingspanLimit;
    private int paxLimit;
    private ArrayList<String> fuelsAvailable;
    private boolean archived;

}
