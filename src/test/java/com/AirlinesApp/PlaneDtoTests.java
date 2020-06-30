package com.AirlinesApp;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.dto.PlaneDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PlaneDtoTests {
    private static final Integer ID = 0;
    private static final String PLANE_NAME = "planeName";
    private static final Integer SEATS_IN_ECONOMIC = 0;
    private static final Integer SEATS_IN_BUISNESS = 0;
    private Airport APID = new Airport();

    private PlaneDto planeDto;
    @Test
    public void objectSerializes(){
        this.planeDto = new PlaneDto(ID, PLANE_NAME, SEATS_IN_ECONOMIC, SEATS_IN_BUISNESS, APID);
        Assert.assertNotNull(planeDto);
        Assert.assertEquals(planeDto.getId(), ID);
        Assert.assertEquals(planeDto.getPlaneName(), PLANE_NAME);
        Assert.assertEquals(planeDto.getSeatsInEconomic(), SEATS_IN_ECONOMIC);
        Assert.assertEquals(planeDto.getSeatsInBuisness(), SEATS_IN_BUISNESS);
        Assert.assertEquals(planeDto.getAPID(), APID);
    }
}
