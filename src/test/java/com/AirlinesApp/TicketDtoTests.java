package com.AirlinesApp;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.dto.TicketDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TicketDtoTests {
    private final static Integer ID = 0;
    private final static String CLASS_NAME = "Economic";
    private final static Long PRICE = 100l;
    private final static Integer SEAT = 3;
    private final static boolean PAID = false;
    private Client CLIENT = new Client();
    private Flight FLIGHT = new Flight();

    private TicketDto ticketDto;
    @Test
    public void objectSerializes(){
        this.ticketDto = new TicketDto(ID, CLASS_NAME, PRICE, SEAT, PAID, CLIENT, FLIGHT);
        Assert.assertNotNull(this.ticketDto);
        Assert.assertEquals(this.ticketDto.getId(), ID);
        Assert.assertEquals(this.ticketDto.getClassName(), CLASS_NAME);
        Assert.assertEquals(this.ticketDto.getPrice(), PRICE);
        Assert.assertEquals(this.ticketDto.getSeat(), SEAT);
        Assert.assertEquals(this.ticketDto.getClientID(), CLIENT);
        Assert.assertEquals(this.ticketDto.getFlightID(), FLIGHT);
    }
}
