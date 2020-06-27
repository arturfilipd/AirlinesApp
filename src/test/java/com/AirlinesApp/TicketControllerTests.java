package com.AirlinesApp;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TicketControllerTests {
    @Autowired
    WebApplicationContext context;
    @Autowired
    MockMvc mvc;
    @Autowired
    TestUtils utils;
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @WithUserDetails("szef")
    @Test
    public void addTicketTest() throws Exception{
        String json = "{" +
                "\"flightId\":\"100\",\n" +
                "\"userId\":\"91\",\n" +
                "\"ticketClass\":\"Economic\",\n" +
                "\"paid\":\"false\"\n" +
                "}";
        mvc.perform(post("/api/tickets/add").contentType("application/json")
                .content(json).header("Authorization", utils.getUserToken("szef", "qwerty"))
        ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("success")));
    }
    @WithUserDetails("szef")
    @Test
    public void addTicketIvalidFlightTest() throws Exception{
        String json = "{" +
                "\"flightId\":\"-1\",\n" +
                "\"userId\":\"1\",\n" +
                "\"ticketClass\":\"Economic\",\n" +
                "\"paid\":\"false\"\n" +
                "}";
        mvc.perform(post("/api/tickets/add").contentType("application/json")
                .content(json).header("Authorization", utils.getUserToken("szef", "qwerty"))
        ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Error: No such flight!")));
    }
    @WithUserDetails("szef")
    @Test
    public void failureAddTicketTest() throws Exception{
        String json = "{" +
                "\"flightId\":\"0\",\n" +
                "\"userId\":\"-1\",\n" +
                "\"ticketClass\":\"Economic\",\n" +
                "\"paid\":\"false\"\n" +
                "}";
        mvc.perform(post("/api/tickets/add").contentType("application/json")
                .content(json).header("Authorization", utils.getUserToken("szef", "qwerty"))
        ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Error")));
    }
    @WithUserDetails("szef")
    @Test
    public void checkInTicketTest() throws Exception{
        String json = "{" +
                "\"ticketID\":\"101\",\n" +
                "\"seat\":\"22\"\n" +
                "}";
        String jwt = utils.getUserToken("szef", "qwerty");
        System.out.println("TOKEN = " + jwt);
        mvc.perform(post("/api/tickets/checkIn").contentType("application/json")
                .content(json).header("Authorization", jwt)
        ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("success")));
    }
    @WithUserDetails("szef")
    @Test
    public void cancelTicketTest() throws Exception{
        String json = "{\"id\":\"100\"}";
        mvc.perform(delete("/api/tickets/delete").contentType("application/json")
                .content(json).header("Authorization", utils.getUserToken("szef", "qwerty"))
        ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("success")));
    }
    @WithUserDetails("szef")
    @Test
    public void failureCancelTicketTest() throws Exception{
        String json = "{\"id\":\"-1\"}";
        mvc.perform(delete("/api/tickets/delete").contentType("application/json")
                .content(json).header("Authorization", utils.getUserToken("szef", "qwerty"))
        ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Error")));
    }

}
