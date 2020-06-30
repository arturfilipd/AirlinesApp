package com.AirlinesApp;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.dto.AirportDto;
import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOError;
import java.io.IOException;
import java.text.ParseException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AirportDtoTests {
    private static final Integer ID = 0;
    private static final String AIRPORT_NAME = "airportName";
    private static final String CITY_CODE = "cityCode";
    private static final String CITY_NAME = "cityName";

    private AirportDto airportDto = new AirportDto();
    @Test
    public void objectSerializes(){
        this.airportDto = new AirportDto(ID, AIRPORT_NAME, CITY_CODE, CITY_NAME);
        Assert.assertTrue(airportDto.equals(airportDto));
        Assert.assertFalse(airportDto.equals(new AirportDto()));
        Assert.assertEquals(airportDto.getAirportName(), AIRPORT_NAME);
        Assert.assertEquals(airportDto.getCode(), CITY_CODE);
        Assert.assertEquals(airportDto.getCityName(), CITY_NAME);
        AirportDto testAirport = new AirportDto(1, "test", "test", "test");
        this.airportDto.setId(1); this.airportDto.setCode("test"); this.airportDto.setAirportName("test"); this.airportDto.setCityName("test");
        Assert.assertEquals(airportDto, testAirport);

    }
}
