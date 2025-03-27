package com.example.aviation_avio;

import com.example.aviation_avio.controller.AirportController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AirportController.class)
@AutoConfigureMockMvc
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGiveAirportByID() throws Exception{

        String jsonResponse = """
                {
                    "name": "London Heathrow",
                    "servedCity": "London",
                    "elevation": 25.00,
                    "coordinates": {
                        "latitude": 51.4775,
                        "longitude": -0.4613889
                    },
                    "variation": 0.41,
                    "country": {
                        "iso2": "GB",
                        "iso3": "GBR",
                        "isoNumeric": 826,
                        "name": "United Kingdom",
                        "officialName": "United Kingdom of Great Britain and Northern Ireland"
                    },
                    "timeZone": "Europe/London",
                    "type": "AIRPORT",
                    "operator": "PUBLIC",
                    "accessibleFor": [
                        "NON_SCHEDULED",
                        "GENERAL_AVIATION",
                        "SCHEDULED",
                        "HELICOPTER",
                        "BUSINESS_AVIATION"
                    ],
                    "ifr": true,
                    "nonScheduledPermission": "SLOT",
                    "nonScheduledPermissionNotes": null,
                    "mandatoryHandling": true,
                    "mandatoryQualification": false,
                    "weightLimit": 9999999.00,
                    "wingspanLimit": 999.00,
                    "paxLimit": 999,
                    "fuelsAvailable": [
                        "JET"
                    ],
                    "aid": "APT-FO61-WBTO",
                    "archived": false
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void shouldGiveAirportAuthorityByID() throws Exception{
        String jsonResponse = """
                [
                    {
                        "type": "OPERATOR",
                        "name": "Heathrow Airport Limited",
                        "parent": "APT-FO61-WBTO",
                        "aid": "AAU-UT96-LE8W",
                        "kind": "AIRPORT"
                    },
                    {
                        "type": "COORDINATOR",
                        "name": "Airport Co-ordination Ltd",
                        "parent": "APT-FO61-WBTO",
                        "aid": "AAU-GHC3-MJ8J",
                        "kind": "AIRPORT"
                    }
                ]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO/authority/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void shouldGiveRunwaysByID() throws Exception{
        String jsonResponse = """
                [
                       {
                           "aid": "RWY-4L9N-QE1O",
                           "helipad": false,
                           "identifier": "09L",
                           "magneticBearing": 90.08,
                           "trueBearing": 89.67,
                           "thresholdElevation": 23.96,
                           "width": 50.0,
                           "surface": "ASPHALT",
                           "endLights": true,
                           "centerLights": true,
                           "centerLightsSpacing": 15,
                           "touchDownZoneLights": true,
                           "instrumental": true,
                           "precision": true,
                           "separate": true,
                           "thresholdCoordinates": {
                               "latitude": 51.4775,
                               "longitude": -0.4849917
                           },
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "RWY-7H53-KPOB",
                           "helipad": false,
                           "identifier": "27R",
                           "magneticBearing": 270.12,
                           "trueBearing": 269.71,
                           "thresholdElevation": 23.8,
                           "width": 50.0,
                           "surface": "ASPHALT",
                           "endLights": true,
                           "centerLights": true,
                           "centerLightsSpacing": 15,
                           "touchDownZoneLights": true,
                           "instrumental": true,
                           "precision": true,
                           "separate": true,
                           "thresholdCoordinates": {
                               "latitude": 51.477675,
                               "longitude": -0.4330556
                           },
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "RWY-2K88-MLXH",
                           "helipad": false,
                           "identifier": "09R",
                           "magneticBearing": 90.09,
                           "trueBearing": 89.68,
                           "thresholdElevation": 22.95,
                           "width": 50.0,
                           "surface": "ASPHALT",
                           "endLights": true,
                           "centerLights": true,
                           "centerLightsSpacing": 15,
                           "touchDownZoneLights": true,
                           "instrumental": true,
                           "precision": true,
                           "separate": true,
                           "thresholdCoordinates": {
                               "latitude": 51.4647917,
                               "longitude": -0.4823139
                           },
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "RWY-AL9M-SMPO",
                           "helipad": false,
                           "identifier": "27L",
                           "magneticBearing": 269.31,
                           "trueBearing": 269.72,
                           "thresholdElevation": 23.41,
                           "width": 50.0,
                           "surface": "ASPHALT",
                           "endLights": true,
                           "centerLights": true,
                           "centerLightsSpacing": 15,
                           "touchDownZoneLights": true,
                           "instrumental": true,
                           "precision": true,
                           "separate": true,
                           "thresholdCoordinates": {
                               "latitude": 51.46495,
                               "longitude": -0.4341
                           },
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "RWY-OMWL-OW1M",
                           "helipad": true,
                           "identifier": "H",
                           "magneticBearing": 0.0,
                           "trueBearing": 269.48,
                           "thresholdElevation": 22.98,
                           "width": 0.0,
                           "surface": null,
                           "endLights": false,
                           "centerLights": false,
                           "centerLightsSpacing": 0,
                           "touchDownZoneLights": false,
                           "instrumental": false,
                           "precision": false,
                           "separate": false,
                           "thresholdCoordinates": {
                               "latitude": 51.4622972,
                               "longitude": -0.4513806
                           },
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       }
                   ]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO/runways/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void shouldGiveAirportAvailabilityByID() throws Exception{
        String jsonResponse = """
                {
                         "openingIndicator": "OPEN",
                         "arff": [
                             {
                                 "validFrom": "2025-01-17T09:55:28.183427993",
                                 "validTo": "2025-01-18T09:55:28.183427992",
                                 "status": "FULL",
                                 "info": {
                                     "icaoCatAirplane": "X",
                                     "faaCatAirplane": null,
                                     "faa139Certified": null,
                                     "catHelicopters": null,
                                     "waterQuantity": null,
                                     "complementaryQuantity": null,
                                     "arffVehicles": null,
                                     "dischargeCapacity": null,
                                     "extensionAvailable": null,
                                     "extensionNotice": null,
                                     "extensionUpToIcao": null,
                                     "firestationRemote": null,
                                     "firestationNotes": null
                                 }
                             }
                         ],
                         "atc": [
                             {
                                 "validFrom": "2025-01-17T09:55:28.183427993",
                                 "validTo": "2025-01-18T09:55:28.183427992",
                                 "status": "FULL",
                                 "info": {
                                     "enRtfAvailable": true,
                                     "afisOnly": false
                                 }
                             }
                         ],
                         "ciq": [
                             {
                                 "validFrom": "2025-01-17T09:55:28.183427993",
                                 "validTo": "2025-01-18T09:55:28.183427992",
                                 "status": "FULL"
                             }
                         ],
                         "movement": [
                             {
                                 "validFrom": "2025-01-17T09:55:28.183427993",
                                 "validTo": "2025-01-17T22:59:59.999999999",
                                 "status": "FULL",
                                 "info": {}
                             },
                             {
                                 "validFrom": "2025-01-17T23:00:00",
                                 "validTo": "2025-01-17T23:59:59.999999999",
                                 "status": "LIMITED",
                                 "info": {
                                     "openFor": [
                                         {
                                             "flightTypes": [
                                                 {
                                                     "rules": [],
                                                     "filing": [],
                                                     "airframe": [],
                                                     "status": [],
                                                     "purpose": [],
                                                     "direction": []
                                                 }
                                             ],
                                             "priorNoticeRequired": null,
                                             "costsInvolved": null,
                                             "other": "Night restrictions are in place. Check operational notes for details."
                                         }
                                     ]
                                 }
                             },
                             {
                                 "validFrom": "2025-01-18T00:00:00",
                                 "validTo": "2025-01-18T06:59:59.999999999",
                                 "status": "LIMITED",
                                 "info": {
                                     "openFor": [
                                         {
                                             "flightTypes": [
                                                 {
                                                     "rules": [],
                                                     "filing": [],
                                                     "airframe": [],
                                                     "status": [],
                                                     "purpose": [],
                                                     "direction": []
                                                 }
                                             ],
                                             "priorNoticeRequired": null,
                                             "costsInvolved": null,
                                             "other": "Night restrictions are in place. Check Operational Notes for details."
                                         }
                                     ]
                                 }
                             },
                             {
                                 "validFrom": "2025-01-18T07:00:00",
                                 "validTo": "2025-01-18T09:55:28.183427992",
                                 "status": "FULL",
                                 "info": {}
                             }
                         ]
                     }
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO/availability"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void shouldGiveRunwaysPagedByID() throws Exception{
        String jsonResponse = """
                {
                       "page": {
                           "number": 1,
                           "size": 2,
                           "totalPages": 3,
                           "totalResults": 5
                       },
                       "content": [
                           {
                               "aid": "RWY-2K88-MLXH",
                               "helipad": false,
                               "identifier": "09R",
                               "magneticBearing": 90.09,
                               "trueBearing": 89.68,
                               "thresholdElevation": 22.95,
                               "width": 50.0,
                               "surface": "ASPHALT",
                               "endLights": true,
                               "centerLights": true,
                               "centerLightsSpacing": 15,
                               "touchDownZoneLights": true,
                               "instrumental": true,
                               "precision": true,
                               "separate": true,
                               "thresholdCoordinates": {
                                   "latitude": 51.4647917,
                                   "longitude": -0.4823139
                               },
                               "parent": "APT-FO61-WBTO",
                               "archived": false
                           },
                           {
                               "aid": "RWY-AL9M-SMPO",
                               "helipad": false,
                               "identifier": "27L",
                               "magneticBearing": 269.31,
                               "trueBearing": 269.72,
                               "thresholdElevation": 23.41,
                               "width": 50.0,
                               "surface": "ASPHALT",
                               "endLights": true,
                               "centerLights": true,
                               "centerLightsSpacing": 15,
                               "touchDownZoneLights": true,
                               "instrumental": true,
                               "precision": true,
                               "separate": true,
                               "thresholdCoordinates": {
                                   "latitude": 51.46495,
                                   "longitude": -0.4341
                               },
                               "parent": "APT-FO61-WBTO",
                               "archived": false
                           }
                       ]
                   }
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO/runways?page=1&size=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void shouldGiveProvidersByID() throws Exception{
        String jsonResponse = """
                [
                       {
                           "aid": "PRV-903A-QYUP",
                           "name": "ON AIR DINING",
                           "website": "http://www.onairdining.com",
                           "phone": "+44 20 3693 3888",
                           "email": "orders@onairdining.com",
                           "country": null,
                           "category": "CATERING",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-2Q7Y-TWVH",
                           "name": "Menzies Fuelling",
                           "website": "http://www.menziesaviation.com",
                           "phone": "+44 20 8897 2836",
                           "email": "anthony.margerison@asig.co.uk",
                           "country": {
                               "iso2": "GB",
                               "iso3": "GBR",
                               "isoNumeric": 826,
                               "name": "United Kingdom",
                               "officialName": "United Kingdom of Great Britain and Northern Ireland"
                           },
                           "category": "FUEL",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-J3OM-1MRT",
                           "name": "HERTZ",
                           "website": "http://www.hertz.com",
                           "phone": "+44 20 8759 1125",
                           "email": "heathrowadmin@hertz.com",
                           "country": null,
                           "category": "CAR_RENTAL",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-LV7N-GKOV",
                           "name": "airssist",
                           "website": "https://airssist.com/",
                           "phone": "+97143682596",
                           "email": "concierge@airssist.com",
                           "country": null,
                           "category": "PRIVATE_TRANSFER",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-YO0F-G1VH",
                           "name": "Menzies Aviation",
                           "website": "http://www.menziesaviation.com",
                           "phone": "+44  7825 112383",
                           "email": "lhrmenzies.ops@menziesaviation.com",
                           "country": {
                               "iso2": "GB",
                               "iso3": "GBR",
                               "isoNumeric": 826,
                               "name": "United Kingdom",
                               "officialName": "United Kingdom of Great Britain and Northern Ireland"
                           },
                           "category": "HANDLING",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-7DJB-2IAX",
                           "name": "Signature Aviation",
                           "website": "https://signatureflight.com/locations/lhr",
                           "phone": "+44 20 8283 2500",
                           "email": "lhr@signatureflight.co.uk",
                           "country": {
                               "iso2": "GB",
                               "iso3": "GBR",
                               "isoNumeric": 826,
                               "name": "United Kingdom",
                               "officialName": "United Kingdom of Great Britain and Northern Ireland"
                           },
                           "category": "HANDLING",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-J5AJ-TVVT",
                           "name": "Drivania Chauffeurs",
                           "website": "drivania.com",
                           "phone": "+44 20 376 1926",
                           "email": "sales@drivania.com",
                           "country": {
                               "iso2": "US",
                               "iso3": "USA",
                               "isoNumeric": 840,
                               "name": "United States",
                               "officialName": "United States of America"
                           },
                           "category": "PRIVATE_TRANSFER",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       },
                       {
                           "aid": "PRV-H7PJ-DQTY",
                           "name": "Swissport Fuelling Services Uk",
                           "website": "http://www.swissport.com",
                           "phone": "+44 20 8564 4900",
                           "email": "keith.bradbury@swissport.com",
                           "country": {
                               "iso2": "GB",
                               "iso3": "GBR",
                               "isoNumeric": 826,
                               "name": "United Kingdom",
                               "officialName": "United Kingdom of Great Britain and Northern Ireland"
                           },
                           "category": "FUEL",
                           "parent": "APT-FO61-WBTO",
                           "archived": false
                       }
                   ]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO/providers/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void shouldGiveProvidersPagedByIDButFail() throws Exception{
        String jsonResponse = """
                {
                     "page": {
                         "number": 2,
                         "size": 2,
                         "totalPages": 4,
                         "totalResults": 8
                     },
                     "content": [
                         {
                             "aid": "PRV-J5AJ-TVVT",
                             "name": "Drivania Chauffeurs",
                             "website": "drivania.com",
                             "phone": "+44 20 376 1926",
                             "email": "sales@drivania.com",
                             "country": {
                                 "iso2": "US",
                                 "iso3": "USA",
                                 "isoNumeric": 840,
                                 "name": "United States",
                                 "officialName": "United States of America"
                             },
                             "category": "PRIVATE_TRANSFER",
                             "parent": "APT-FO61-WBTO",
                             "archived": false
                         },
                         {
                             "aid": "PRV-H7PJ-DQTY",
                             "name": "Swissport Fuelling Services Uk",
                             "website": "http://www.swissport.com",
                             "phone": "+44 20 8564 4900",
                             "email": "keith.bradbury@swissport.com",
                             "country": {
                                 "iso2": "GB",
                                 "iso3": "GBR",
                                 "isoNumeric": 826,
                                 "name": "United Kingdom",
                                 "officialName": "United Kingdom of Great Britain and Northern Ireland"
                             },
                             "category": "FUEL",
                             "parent": "APT-FO61-WBTO",
                             "archived": false
                         }
                     ]
                 }
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/apps/airports/APT-FO61-WBTO/providers?size=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

}
