package com.AirlinesApp;
import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.dto.ClientDto;
import com.AirlinesApp.dto.FlightDto;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FlightDtoTests {
        private final static Integer ID = 0;
        private final static Long PRICE_ECONOMIC = 100l;
        private final static Date S_DATE = new Date();
        private final static Date E_DATE = new Date();
        private final static Long PRICE_BUISNESS = 100l;
        private  Airport S_APID = new Airport();
        private  Airport D_APID = new Airport();
        private  Plane PLANE = new Plane();

        private FlightDto flightDto;
        @Test
        public void objectSerializes(){
            this.flightDto = new FlightDto(ID, PRICE_ECONOMIC, S_DATE, E_DATE, PRICE_BUISNESS, S_APID, D_APID, PLANE);
            Assert.assertNotNull(this.flightDto);
            Assert.assertEquals(this.flightDto.getId(), ID);
            Assert.assertEquals(this.flightDto.getPriceEconomic(), PRICE_ECONOMIC);
            Assert.assertEquals(this.flightDto.getPriceBuisness(), PRICE_BUISNESS);
            Assert.assertEquals(this.flightDto.getSAPID(), S_APID);
            Assert.assertEquals(this.flightDto.getDAPID(), D_APID);
            Assert.assertEquals(this.flightDto.getPlaneID(), PLANE);
        }
}
