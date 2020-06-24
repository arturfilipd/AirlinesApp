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
public class PlaneControllerTest {

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
    public void addPlaneValid(){
        String json = "{\n" +
                "\"airportId\": 100,\n" +
                "\"businessSeats\": 20,\n" +
                "\"ecoSeats\": 50,\n" +
                "\"name\": \"testPlane\"\n" +
                "}";
        try {
            mvc.perform(post("/api/planes/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("szef")
    public void addPlaneNoAirport(){
        String json = "{\n" +
                "\"airportId\": -1,\n" +
                "\"businessSeats\": 20,\n" +
                "\"ecoSeats\": 50,\n" +
                "\"name\": \"testPlane\"\n" +
                "}";
        try {
            mvc.perform(post("/api/planes/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Invalid airport!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("szef")
    public void listPlanes(){
        try{
            mvc.perform(MockMvcRequestBuilders.get("/api/planes/list"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("PlanesListTest")));
            }catch (Exception e){e.printStackTrace();}
    }

    @Test
    @WithUserDetails("szef")
    public void planesByAirport(){
        String json = "{\n" +
                "\"airportId\": 100\n" +
                "}";
        try {
            mvc.perform(MockMvcRequestBuilders.get("/api/planes/getByAirport").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("PlanesListTest")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("szef")
    public void planesByAirportInvalid(){
        String json = "{\n" +
                "\"airportId\": -1\n" +
                "}";
        try {
            mvc.perform(post("/api/planes/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}


