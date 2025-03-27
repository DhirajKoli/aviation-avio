package com.example.aviation_avio.cucumber;

import com.example.aviation_avio.AviationAvioApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = { AviationAvioApplication.class,
                            CucumberIT.class},
                            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features")
public class CucumberIT {
}
