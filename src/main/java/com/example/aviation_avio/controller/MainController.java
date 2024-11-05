package com.example.aviation_avio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/apps")
public class MainController {

    @Autowired
    private Environment environment;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Welcome aboard on Aviation Pro",HttpStatus.OK);
    }

    @GetMapping(value = "/callclienthello")
    private ResponseEntity<String> getHelloClient(){

        HttpHeaders headers= new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(environment.getProperty("api.token"));
        HttpEntity <String> entity = new HttpEntity<>(headers);

        String uri = "https://api.aviowiki.com/airports/search?query=London";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(uri, HttpMethod.GET,entity, String.class);
    }
}
