package com.example.aviation_avio.api.service;

import com.example.aviation_avio.api.response.*;
import com.example.aviation_avio.service.AirportServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @Mock
    private Environment environment;

    @InjectMocks
    private AirportServiceImp airportServiceImp;

    @BeforeEach
    public void doBefore(){
        String uri = "https://api.aviowiki.com/airports";
        String token = "589097e2-6804-4b3b-bd1a-eadd1bebaeb9";

        Mockito.when(environment.getProperty("api.uri")).thenReturn(uri);
        Mockito.when(environment.getProperty("api.token")).thenReturn(token);
    }

    @Test
    public void apiUrlnotRespondingWithStatus200(){

    }

    @Test
    public void shouldReturnAirportResponseObjectById(){
        AirportResponse expectedAirportResponse = AirportResponse.builder()
                .aid("APT-FO61-WBTO")
                .name("London Heathrow")
                .servedCity("London")
                .elevation(25.0)
                .coordinates(
                        Coordinates.builder()
                                .latitude(51.4775)
                                .longitude(-0.4613889)
                                .build()
                )
                .variation(0.41)
                .country(
                        Country.builder()
                                .iso2("GB")
                                .iso3("GBR")
                                .isoNumeric(826)
                                .name("United Kingdom")
                                .officialName("United Kingdom of Great Britain and Northern Ireland")
                                .build()
                )
                .timeZone("Europe/London")
                .type("AIRPORT")
                .operator("PUBLIC")
                .accessibleFor(new ArrayList<String>(
                        Arrays.asList("HELICOPTER",
                                "GENERAL_AVIATION",
                                "BUSINESS_AVIATION",
                                "NON_SCHEDULED",
                                "SCHEDULED")))
                .ifr(true)
                .nonScheduledPermission("SLOT")
                .nonScheduledPermissionNotes(null)
                .mandatoryHandling(true)
                .mandatoryQualification(false)
                .weightLimit(9999999.0)
                .wingspanLimit(999.0)
                .paxLimit(999)
                .fuelsAvailable(new ArrayList<String>(
                        Arrays.asList("JET")))
                .archived(false)
                .build();

        AirportResponse airportResponse = airportServiceImp.getAirportByID("APT-FO61-WBTO");

        Assertions.assertThat(airportResponse)
                .isNotNull()
                .isEqualToIgnoringGivenFields(
                        expectedAirportResponse,
                        "country",
                        "coordinates",
                        "accessibleFor");

        Assertions.assertThat(airportResponse.getCoordinates())
                .isEqualToComparingFieldByField(expectedAirportResponse.getCoordinates());

        Assertions.assertThat(airportResponse.getCountry())
                .isEqualToComparingFieldByField(expectedAirportResponse.getCountry());
    }

    @Test
    public void shouldReturnListOfAuthority(){
        List<Authority> expectedAuthorityList = new ArrayList<Authority>(){
            {
                add(Authority.builder()
                        .aid("AAU-UT96-LE8W")
                        .name("Heathrow Airport Limited")
                        .type("OPERATOR")
                        .parent("APT-FO61-WBTO")
                        .kind("AIRPORT")
                        .build());
                add(Authority.builder()
                        .aid("AAU-GHC3-MJ8J")
                        .name("Airport Co-ordination Ltd")
                        .type("COORDINATOR")
                        .parent("APT-FO61-WBTO")
                        .kind("AIRPORT")
                        .build());
            }
        };

        List<Authority> authorities = airportServiceImp.getAirportAuthorityByID("APT-FO61-WBTO");

        Assertions.assertThat(authorities)
                .isNotNull();

        Assertions.assertThat(authorities.size()).isEqualTo(expectedAuthorityList.size());

        for(int i = 0;i<expectedAuthorityList.size();i++)
            Assertions.assertThat(authorities.get(i)).isEqualToComparingFieldByField(expectedAuthorityList.get(i));

    }

    @Test
    public void shouldReturnPagedAuthorityResponse(){
        PageResponse expectedPageResponse =
                PageResponse.builder()
                        .page(
                                Page.builder()
                                        .number(1)
                                        .size(1)
                                        .totalPages(2)
                                        .totalResults(2)
                                        .build()
                        )
                        .content(
                                new ArrayList<>(){
                                    {
                                        add(Authority.builder()
                                                .aid("AAU-GHC3-MJ8J")
                                                .name("Airport Co-ordination Ltd")
                                                .type("COORDINATOR")
                                                .parent("APT-FO61-WBTO")
                                                .kind("AIRPORT")
                                                .build());
                                    }
                                }
                        )
                        .build();

        PageResponse pageResponse = airportServiceImp.getAirportAuthorityPagedByID("APT-FO61-WBTO", Optional.of("1"), Optional.of("1"));

        Assertions.assertThat(pageResponse).isNotNull();

        Assertions.assertThat(pageResponse.getPage())
                .isEqualToComparingFieldByField(expectedPageResponse.getPage());

        Assertions.assertThat(pageResponse.getContent().size())
                .isEqualTo(expectedPageResponse.getContent().size());

        for(int i = 0; i< expectedPageResponse.getContent().size(); i++)
            Assertions.assertThat(pageResponse.getContent().get(i))
                    .isEqualToComparingFieldByField(expectedPageResponse.getContent().get(i));
    }

    @Test
    public void shouldReturnListOfRunways(){
        List<Runway> expectedRunways = new ArrayList<Runway>(){
            {
                add(Runway.builder()
                        .aid("RWY-4L9N-QE1O")
                        .helipad(false)
                        .identifier("09L")
                        .magneticBearing(90.08)
                        .trueBearing(89.67)
                        .thresholdElevation(23.96)
                        .width(50.00)
                        .surface("ASPHALT")
                        .endLights(true)
                        .centerLights(true)
                        .centerLightsSpacing(15)
                        .touchDownZoneLights(true)
                        .instrumental(true)
                        .precision(true)
                        .separate(true)
                        .thresholdCoordinates(
                                Coordinates.builder()
                                        .latitude(51.4775)
                                        .longitude(-0.4849917)
                                        .build()
                        )
                        .parent("APT-FO61-WBTO")
                        .archived(false)
                        .build());

                add(Runway.builder()
                        .aid("RWY-7H53-KPOB")
                        .helipad(false)
                        .identifier("27R")
                        .magneticBearing(270.12)
                        .trueBearing(269.71)
                        .thresholdElevation(23.8)
                        .width(50.0)
                        .surface("ASPHALT")
                        .endLights(true)
                        .centerLights(true)
                        .centerLightsSpacing(15)
                        .touchDownZoneLights(true)
                        .instrumental(true)
                        .precision(true)
                        .separate(true)
                        .thresholdCoordinates(
                                Coordinates.builder()
                                        .latitude(51.477675)
                                        .longitude(-0.4330556)
                                        .build()
                        )
                        .parent("APT-FO61-WBTO")
                        .archived(false)
                        .build());

                add(Runway.builder()
                        .aid("RWY-2K88-MLXH")
                        .helipad(false)
                        .identifier("09R")
                        .magneticBearing(90.09)
                        .trueBearing(89.68)
                        .thresholdElevation(22.95)
                        .width(50.0)
                        .surface("ASPHALT")
                        .endLights(true)
                        .centerLights(true)
                        .centerLightsSpacing(15)
                        .touchDownZoneLights(true)
                        .instrumental(true)
                        .precision(true)
                        .separate(true)
                        .thresholdCoordinates(
                                Coordinates.builder()
                                        .latitude(51.4647917)
                                        .longitude(-0.4823139)
                                        .build()
                        )
                        .parent("APT-FO61-WBTO")
                        .archived(false)
                        .build());

                add(Runway.builder()
                        .aid("RWY-AL9M-SMPO")
                        .helipad(false)
                        .identifier("27L")
                        .magneticBearing(269.31)
                        .trueBearing(269.72)
                        .thresholdElevation(23.41)
                        .width(50.0)
                        .surface("ASPHALT")
                        .endLights(true)
                        .centerLights(true)
                        .centerLightsSpacing(15)
                        .touchDownZoneLights(true)
                        .instrumental(true)
                        .precision(true)
                        .separate(true)
                        .thresholdCoordinates(
                                Coordinates.builder()
                                        .latitude(51.46495)
                                        .longitude(-0.4341)
                                        .build()
                        )
                        .parent("APT-FO61-WBTO")
                        .archived(false)
                        .build());

                add(Runway.builder()
                        .aid("RWY-OMWL-OW1M")
                        .helipad(true)
                        .identifier("H")
                        .magneticBearing(0.0)
                        .trueBearing(269.48)
                        .thresholdElevation(22.98)
                        .width(0.0)
                        .surface(null)
                        .endLights(false)
                        .centerLights(false)
                        .centerLightsSpacing(0)
                        .touchDownZoneLights(false)
                        .instrumental(false)
                        .precision(false)
                        .separate(false)
                        .thresholdCoordinates(
                                Coordinates.builder()
                                        .latitude(51.4622972)
                                        .longitude(-0.4513806)
                                        .build()
                        )
                        .parent("APT-FO61-WBTO")
                        .archived(false)
                        .build());
            }
        };

        List<Runway> runways = airportServiceImp.getRunwaysByID("APT-FO61-WBTO");

        Assertions.assertThat(runways)
                .isNotNull();

        Assertions.assertThat(runways.size()).isEqualTo(expectedRunways.size());

        for(int i = 0;i<expectedRunways.size();i++) {
            Assertions.assertThat(runways.get(i)).isEqualToIgnoringGivenFields(expectedRunways.get(i), "thresholdCoordinates");
            Assertions.assertThat(runways.get(i).getThresholdCoordinates()).isEqualToComparingFieldByField(expectedRunways.get(i).getThresholdCoordinates());
        }
    }

    @Test
    public void shouldReturnPagedRunwaysResponse(){

        PageResponse expectedPageResponse = PageResponse.builder()
                        .page(
                                Page.builder()
                                        .number(1)
                                        .size(2)
                                        .totalPages(3)
                                        .totalResults(5)
                                        .build()
                        )
                        .content(
                                new ArrayList<>(){
                                    {
                                        add(Runway.builder()
                                                .aid("RWY-2K88-MLXH")
                                                .helipad(false)
                                                .identifier("09R")
                                                .magneticBearing(90.09)
                                                .trueBearing(89.68)
                                                .thresholdElevation(22.95)
                                                .width(50.0)
                                                .surface("ASPHALT")
                                                .endLights(true)
                                                .centerLights(true)
                                                .centerLightsSpacing(15)
                                                .touchDownZoneLights(true)
                                                .instrumental(true)
                                                .precision(true)
                                                .separate(true)
                                                .thresholdCoordinates(
                                                        Coordinates.builder()
                                                                .latitude(51.4647917)
                                                                .longitude(-0.4823139)
                                                                .build()
                                                )
                                                .parent("APT-FO61-WBTO")
                                                .archived(false)
                                                .build());

                                        add(Runway.builder()
                                                .aid("RWY-AL9M-SMPO")
                                                .helipad(false)
                                                .identifier("27L")
                                                .magneticBearing(269.31)
                                                .trueBearing(269.72)
                                                .thresholdElevation(23.41)
                                                .width(50.0)
                                                .surface("ASPHALT")
                                                .endLights(true)
                                                .centerLights(true)
                                                .centerLightsSpacing(15)
                                                .touchDownZoneLights(true)
                                                .instrumental(true)
                                                .precision(true)
                                                .separate(true)
                                                .thresholdCoordinates(
                                                        Coordinates.builder()
                                                                .latitude(51.46495)
                                                                .longitude(-0.4341)
                                                                .build()
                                                )
                                                .parent("APT-FO61-WBTO")
                                                .archived(false)
                                                .build());
                                    }
                                }
                        )
                        .build();

        PageResponse pageResponse = airportServiceImp.getRunwaysPagedByID("APT-FO61-WBTO", Optional.of("1"), Optional.of("2"));

        Assertions.assertThat(pageResponse)
                .isNotNull();

        Assertions.assertThat(pageResponse.getPage())
                        .isNotNull()
                                .isEqualToComparingFieldByField(expectedPageResponse.getPage());

        Assertions.assertThat(pageResponse.getContent())
                .isNotNull()
                .hasSameSizeAs(expectedPageResponse.getContent());


        for(int i = 0;i<expectedPageResponse.getContent().size();i++) {
            Assertions.assertThat(pageResponse.getContent().get(i)).isEqualToIgnoringGivenFields(expectedPageResponse.getContent().get(i), "thresholdCoordinates");
            Runway runway = (Runway)pageResponse.getContent().get(i);
            Runway expectedRunway = (Runway) expectedPageResponse.getContent().get(i);
            Assertions.assertThat(runway.getThresholdCoordinates()).isEqualToComparingFieldByField(expectedRunway.getThresholdCoordinates());
        }
    }

    @Test
    public void shouldFailButReturnPagedProviderResponse(){

        PageResponse expectedPageResponse =
                PageResponse.builder()
                        .page(
                                Page.builder()
                                        .number(2)
                                        .size(2)
                                        .totalPages(4)
                                        .totalResults(8)
                                        .build()
                        )
                        .content(
                                new ArrayList<>(){
                                    {
                                        add(Provider.builder()
                                                .aid("PRV-2Q7Y-TWVH")
                                                .name("Menzies Fuelling")
                                                .website("http://www.menziesaviation.com")
                                                .phone("+44 20 8897 2836")
                                                .email("anthony.margerison@asig.co.uk")
                                                .category("FUEL")
                                                .country(
                                                        Country.builder()
                                                                .iso2("GB")
                                                                .iso3("GBR")
                                                                .isoNumeric(826)
                                                                .name("United Kingdom")
                                                                .officialName("United Kingdom of Great Britain and Northern Ireland")
                                                                .build()
                                                )
                                                .parent("APT-FO61-WBTO")
                                                .archived(false)
                                                .build());

                                        add(Provider.builder()
                                                .aid("PRV-J3OM-1MRT")
                                                .name("HERTZ")
                                                .website("http://www.hertz.com")
                                                .phone("+44 20 8759 1125")
                                                .email("heathrowadmin@hertz.com")
                                                .category("CAR_RENTAL")
                                                .country(
                                                        null
                                                )
                                                .parent("APT-FO61-WBTO")
                                                .archived(false)
                                                .build());
                                    }
                                }
                        )
                        .build();


        PageResponse pageResponse = airportServiceImp.getProvidersPagedByID("APT-FO61-WBTO", Optional.of("2"), Optional.of("2"));

        Assertions.assertThat(pageResponse).isNotNull();

        Assertions.assertThat(pageResponse.getPage())
                .isNotNull()
                .isEqualToComparingFieldByField(expectedPageResponse.getPage());

        for(int i = 0;i<expectedPageResponse.getContent().size();i++) {
            Provider provider = (Provider) pageResponse.getContent().get(i);
            Provider expectedProvider  = (Provider) expectedPageResponse.getContent().get(i);
            Assertions.assertThat(provider.getAid()).isNotEqualTo(expectedProvider.getAid());
        }

    }
}
