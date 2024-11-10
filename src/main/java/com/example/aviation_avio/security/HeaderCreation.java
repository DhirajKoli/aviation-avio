package com.example.aviation_avio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HeaderCreation {

    @Autowired
    private Environment environment;

    public HttpEntity<String> getHeader(){
        HttpHeaders headers= new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(environment.getProperty("api.token"));
        return new HttpEntity<>(headers);
    }
}
