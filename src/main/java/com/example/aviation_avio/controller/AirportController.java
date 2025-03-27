package com.example.aviation_avio.controller;

import com.example.aviation_avio.api.response.*;
import com.example.aviation_avio.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apps/airports")
@ComponentScan("com.example.aviation_avio.*")
public class AirportController {
    
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    AirportService airportService;

    @GetMapping("/{aid}")
    private ResponseEntity<AirportResponse> getAirportByID(
            @PathVariable("aid") String aid){

        return ResponseEntity.ok(airportService.getAirportByID(aid));
    }

    @GetMapping("/{aid}/authority/all")
    private ResponseEntity<List<Authority>> getAirportAuthorityByID(
            @PathVariable("aid") String aid){
        return ResponseEntity.ok(airportService.getAirportAuthorityByID(aid));
    }

    @GetMapping("/{aid}/authority")
    private ResponseEntity<PageResponse<Authority>> getAirportAuthorityPagedByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size){

        return ResponseEntity.ok(airportService.getAirportAuthorityPagedByID(aid,page,size));
    }

    @GetMapping("/{aid}/runways/all")
    private ResponseEntity<List<Runway>> getRunwaysByID(
            @PathVariable("aid") String aid){

        return ResponseEntity.ok(airportService.getRunwaysByID(aid));
    }

    @GetMapping("/{aid}/runways")
    private ResponseEntity<PageResponse<Runway>> getRunwaysPagedByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size){

        return ResponseEntity.ok(airportService.getRunwaysPagedByID(aid,page,size));
    }

    @GetMapping("/{aid}/providers/all")
    private ResponseEntity<List<Provider>> getProvidersByID(
            @PathVariable("aid") String aid){

        return ResponseEntity.ok(airportService.getProvidersByID(aid));
    }

    @GetMapping("/{aid}/providers")
    private ResponseEntity<PageResponse<Provider>> getProvidersPagedByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size){

        return ResponseEntity.ok(airportService.getProvidersPagedByID(aid,page,size));
    }

    @GetMapping("/{aid}/availability")
    private ResponseEntity<String> getAirportAvailabilityByID(
            @PathVariable("aid") String aid){

        return ResponseEntity.ok(airportService.getAirportAvailabilityByID(aid));
    }
}
