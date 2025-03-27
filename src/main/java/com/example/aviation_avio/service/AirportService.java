package com.example.aviation_avio.service;

import com.example.aviation_avio.api.model.*;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    public AirportResponse getAirportByID(String aid);

    public List<Authority> getAirportAuthorityByID(String aid);

    public PageResponse<Authority> getAirportAuthorityPagedByID(String aid, Optional<String> page, Optional<String> size);

    public List<Runway> getRunwaysByID(String aid);

    public PageResponse<Runway> getRunwaysPagedByID(String aid,Optional<String> page, Optional<String> size);

    public List<Provider> getProvidersByID(String aid);

    public PageResponse<Provider> getProvidersPagedByID(String aid, Optional<String> page, Optional<String> size);

    public String getAirportAvailabilityByID(String aid);

    public String apiWorkingChecker() throws Exception;
}
