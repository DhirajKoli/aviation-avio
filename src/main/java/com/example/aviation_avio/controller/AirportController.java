package com.example.aviation_avio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/apps/airports")
public class AirportController {

    @Autowired
    private Environment environment;

    @GetMapping("/search")
    private ResponseEntity<String> searchAirports(@RequestParam Optional<String> query, @RequestParam Optional<String> country){

        HttpHeaders headers= new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(environment.getProperty("api.token"));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        String uri;
        if(query.isPresent()) {
            uri = "https://api.aviowiki.com/airports/search?query={query}";
            return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class,query.get());
        }
        uri = "https://api.aviowiki.com/airports/search";
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);




    }
}
