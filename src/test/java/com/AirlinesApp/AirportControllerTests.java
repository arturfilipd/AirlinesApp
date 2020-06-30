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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AirportControllerTests {
    @Autowired
    WebApplicationContext context;
    @Autowired
    MockMvc mvc;
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithUserDetails("szef")
    @Test
    public void addAirportTest(){
        String json = "{\n" +
                "\"name\":\"test\",\n" +
                "\"code\":\"TST\",\n" +
                "\"city\":\"test\"\n" +
                "}";
        try {
            mvc.perform(post("/api/airports/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @WithUserDetails("szef")
    @Test
    public void listAirportsTest(){
        try{
            mvc.perform(get("/api/airports/list"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json")).andExpect(content().string(containsString("id")));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}