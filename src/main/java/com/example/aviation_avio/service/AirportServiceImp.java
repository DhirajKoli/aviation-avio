package com.example.aviation_avio.service;

import com.example.aviation_avio.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.example.aviation_avio.*")
public class AirportServiceImp implements AirportService{

    @Autowired
    private Environment environment;

    RestTemplate restTemplate = new RestTemplate();
//    public String uri;


//    public AirportServiceImp(Environment environment) {
//        this.environment = environment;
//        uri = this.environment.getProperty("api.uri");
//    }

    public HttpEntity<String> getHeader(){
        HttpHeaders headers= new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(environment.getProperty("api.token"));
        return new HttpEntity<>(headers);
    }

    @Override
    public AirportResponse getAirportByID(String aid) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}";

        return restTemplate.exchange(uri, HttpMethod.GET,this.getHeader(), AirportResponse.class,aid).getBody();
    }

    @Override
    public List<Authority> getAirportAuthorityByID(String aid) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/airportAuthorities/all";

        return restTemplate.exchange(uri, HttpMethod.GET,this.getHeader(), new ParameterizedTypeReference<List<Authority>>() {},aid).getBody();
    }

    @Override
    public PageResponse<Authority> getAirportAuthorityPagedByID(String aid, Optional<String> page, Optional<String> size) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/airportAuthorities?page={page}&size={size}";

        return restTemplate.exchange(uri, HttpMethod.GET,this.getHeader(), new ParameterizedTypeReference<PageResponse<Authority>>(){},
                aid,
                page.isPresent()?page.get():0,
                size.isPresent()?size.get():10).getBody();
    }

    @Override
    public List<Runway> getRunwaysByID(String aid) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/runways/all";

        return restTemplate.exchange(uri, HttpMethod.GET,this.getHeader(), new ParameterizedTypeReference<List<Runway>>() {},aid).getBody();
    }

    @Override
    public PageResponse<Runway> getRunwaysPagedByID(String aid, Optional<String> page, Optional<String> size) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/runways?page={page}&size={size}";

        return restTemplate.exchange(uri, HttpMethod.GET, this.getHeader(),
                new ParameterizedTypeReference<PageResponse<Runway>>() {},
                aid,
                page.isPresent() ? page.get() : 0,
                size.isPresent() ? size.get() : 10).getBody();
    }

    @Override
    public List<Provider> getProvidersByID(String aid) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/providers/all";

        return restTemplate.exchange(uri, HttpMethod.GET, this.getHeader(), new ParameterizedTypeReference<List<Provider>>() {
        }, aid).getBody();
    }

    @Override
    public PageResponse<Provider> getProvidersPagedByID(String aid, Optional<String> page, Optional<String> size) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/providers?page={page}&size={size}";

        return restTemplate.exchange(uri, HttpMethod.GET, this.getHeader(),
                new ParameterizedTypeReference<PageResponse<Provider>>() {
                },
                aid,
                page.isPresent() ? page.get() : 0,
                size.isPresent() ? size.get() : 10).getBody();
    }

    @Override
    public String getAirportAvailabilityByID(String aid) {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/{aid}/availability";

        return restTemplate.exchange(uri, HttpMethod.GET,this.getHeader(), String.class,aid).getBody();
    }

    @Override
    public String apiWorkingChecker() throws HttpClientErrorException {

        String uri = environment.getProperty("api.uri");

        uri = uri + "/APT-FO61-WBTO/runways?page=1&size=2";

        ResponseEntity<String> stringResponseEntity;

        try{
            stringResponseEntity = restTemplate.exchange(uri, HttpMethod.GET,this.getHeader(),String.class);
        }catch(HttpClientErrorException e){
            throw new HttpClientErrorException(e.getStatusCode());
        }
        return stringResponseEntity.getBody();
    }


}
