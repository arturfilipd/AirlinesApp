package com.AirlinesApp;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Repository.AirportRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AirlinesAppApplicationTests {
    @Autowired
    AirportRepository airportRepository;
    @Test
    void contextLoads() {

    }

}