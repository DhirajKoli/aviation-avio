package com.example.aviation_avio.controller;

import com.example.aviation_avio.security.HeaderCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/apps/airports")
public class AirportController {

    @Autowired
    private HeaderCreation headerCreation;

    @Autowired
    private Environment environment;

    @GetMapping("/search")
    private ResponseEntity<String> searchAirports(
            @RequestParam Optional<String> query,
            @RequestParam Optional<String> country){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        if(query.isPresent()) {
            uri = uri+"/search?query={query}";
            return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,query.get());
        }
        uri = uri+"/search";
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

    }

    @GetMapping("/{aid}")
    private ResponseEntity<String> getAirportByID(
            @PathVariable("aid") String aid){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid);
    }

    @GetMapping("/{aid}/authority/all")
    private ResponseEntity<String> getAirportAuthorityByID(
            @PathVariable("aid") String aid){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/airportAuthorities/all";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid);
    }

    @GetMapping("/{aid}/authority")
    private ResponseEntity<String> getAirportAuthorityPagedByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/airportAuthorities?page={page}&size={size}";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid,
                page.isPresent()?page.get():0,
                size.isPresent()?size.get():10);
    }

    @GetMapping("/{aid}/availability")
    private ResponseEntity<String> getAirportAvailabilityByID(
            @PathVariable("aid") String aid){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/availability";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid);
    }

    @GetMapping("/{aid}/runways/all")
    private ResponseEntity<String> getRunwaysByID(
            @PathVariable("aid") String aid){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/runways/all";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid);
    }

    @GetMapping("/{aid}/runways")
    private ResponseEntity<String> getRunwaysPagedByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/runways?page={page}&size={size}";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid,
                page.isPresent()?page.get():0,
                size.isPresent()?size.get():10);
    }

    @GetMapping("/{aid}/providers/all")
    private ResponseEntity<String> getProvidersByID(
            @PathVariable("aid") String aid){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/providers/all";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid);
    }

    @GetMapping("/{aid}/providers")
    private ResponseEntity<String> getProvidersPagedByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> page,
            @RequestParam Optional<String> size){

        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/providers?page={page}&size={size}";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid,
                page.isPresent()?page.get():0,
                size.isPresent()?size.get():10);
    }

    @GetMapping("/{aid}/sun")
    private ResponseEntity<String> getSunByID(
            @PathVariable("aid") String aid,
            @RequestParam Optional<String> date,
            @RequestParam Optional<String> local){
        HttpEntity<String> entity = headerCreation.getHeader();

        RestTemplate restTemplate = new RestTemplate();

        String uri = environment.getProperty("api.uri");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        uri = uri + "/{aid}/sun?date={date}&local={local}";

        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,aid,
                date.isPresent()?date.get():LocalDate.now().format(formatter),
                local.isPresent()?local.get():false);
    }
}
