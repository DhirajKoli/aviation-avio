package com.example.aviation_avio.cucumber.glue;

import com.example.aviation_avio.api.model.AirportResponse;
import com.example.aviation_avio.service.AirportService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class AirportSteps {

    private String airportId;

    private AirportResponse actualResponse;

    @Autowired
    private AirportService airportService;


    @Given("^the airport id (.*)$")
    public void givenTheAirportId(final String id){
        airportId = id;
    }

    @When("^the user requests airport data$")
    public void whenTheUserRequestsAirportData(){
        actualResponse = airportService.getAirportByID(airportId);
    }

    @Then("^airport data is returned$")
    public void thenAirportDataIsReturned(){
        Assertions.assertThat(actualResponse)
                .isNotNull();
    }

}
