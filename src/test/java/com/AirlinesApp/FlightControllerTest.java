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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mvc;
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @WithUserDetails("szef")
    public void addFlight(){
        String json = "{\n" +
                "\"starts\": \"2020-06-22T00:00:00.000+00:00\",\n" +
                "\"ends\":  \"2020-07-23T00:00:00.000+00:00\",\n" +
                "\"destID\": 100,\n" +
                "\"sourceID\": 101,\n" +
                "\"planeID\": 102,\n" +
                "\"priceEco\": 12,\n" +
                "\"priceBusi\": 120\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @WithUserDetails("szef")
    public void addFlightInvalidDest(){
        String json = "{\n" +
                "\"starts\": \"2020-06-22T00:00:00.000+00:00\",\n" +
                "\"ends\":  \"2020-07-23T00:00:00.000+00:00\",\n" +
                "\"destID\": -1,\n" +
                "\"sourceID\": 101,\n" +
                "\"planeID\": 102,\n" +
                "\"priceEco\": 12,\n" +
                "\"priceBusi\": 120\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Invalid destination airport!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @WithUserDetails("szef")
    public void addFlightInvalidSrc(){
        String json = "{\n" +
                "\"starts\": \"2020-06-22T00:00:00.000+00:00\",\n" +
                "\"ends\":  \"2020-07-23T00:00:00.000+00:00\",\n" +
                "\"destID\": 100,\n" +
                "\"sourceID\": -1,\n" +
                "\"planeID\": 102,\n" +
                "\"priceEco\": 12,\n" +
                "\"priceBusi\": 120\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Invalid source airport!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @WithUserDetails("szef")
    public void addFlightInvalidPlane(){
        String json = "{\n" +
                "\"starts\": \"2020-06-22T00:00:00.000+00:00\",\n" +
                "\"ends\":  \"2020-07-23T00:00:00.000+00:00\",\n" +
                "\"destID\": 100,\n" +
                "\"sourceID\": 101,\n" +
                "\"planeID\": -1,\n" +
                "\"priceEco\": 12,\n" +
                "\"priceBusi\": 120\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Invalid plane id!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @WithUserDetails("szef")
    public void delete(){
        String json = "{\n" +
                "\"id\": 100\n" +
                "}";
        try {
            mvc.perform(MockMvcRequestBuilders.delete("/api/flights/delete").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Flight removed successfully!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @WithUserDetails("szef")
    public void deleteInvalid(){
        String json = "{\n" +
                "\"id\": -1\n" +
                "}";
        try {
            mvc.perform(MockMvcRequestBuilders.delete("/api/flights/delete").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: No such flight!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("szef")
    public void edit(){
        String json = "{\n" +
                "\"id\": 111,\n" +
                "\"newStart\": \"2020-01-21\",\n" +
                "\"newEnd\":\"2020-01-21\"\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/update").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Flight edited successfully!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("szef")
    public void editInvalidFlight(){
        String json = "{\n" +
                "\"id\": -1,\n" +
                "\"newStart\": \"2020-06-22T00:00:00.000+00:00\",\n" +
                "\"newEnd\":\"2020-06-22T00:00:00.000+00:00\"\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/update").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: No such flight!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @WithUserDetails("szef")
    public void editInvalidEndDate(){
        String json = "{\n" +
                "\"id\": 111,\n" +
                "\"newStart\": \"2020-06-22T00:00:00.000+00:00\"\n" +
                "}";
        try {
            mvc.perform(post("/api/flights/update").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Invalid date!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
