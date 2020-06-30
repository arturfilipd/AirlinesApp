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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthControllerTest {
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

    @Autowired
    TestUtils utils;

    @Test
    public void authTest() {
        String json = "{\n" +
                "    \"username\": \"signuptester\",\n" +
                "    \"email\": \"typ22s22@typ.com\",\n" +
                "    \"password\": \"qwerty\",\n" +
                "    \"role\": [\"user\"],\n" +
                "    \"name\": \"Franek\",\n" +
                "    \"surname\": \"Kimono\",\n" +
                "    \"phoneNumber\": \"445666768\",\n" +
                "    \"personalID\": \"123452331\"\n" +
                "}";

        try {
            mvc.perform(post("/api/auth/signup").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signInNameError(){
        //Signup - Username error
        String json = "{\n" +
                "    \"username\": \"szef\",\n" +
                "    \"email\": \"mail@oftheyear.com\",\n" +
                "    \"password\": \"qwerty\",\n" +
                "    \"role\": [\"user\"],\n" +
                "    \"name\": \"Franek\",\n" +
                "    \"surname\": \"Kimono\",\n" +
                "    \"phoneNumber\": \"445666768\",\n" +
                "    \"personalID\": \"123452331\"\n" +
                "}";
        try {
            mvc.perform(post("/api/auth/signup").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Username is already taken!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void signInEmailError(){
        String json = "{\n" +
                "    \"username\": \"testuser3\",\n" +
                "    \"email\": \"employee@oftheyear.com2\",\n" +
                "    \"password\": \"qwerty\",\n" +
                "    \"role\": [\"user\"],\n" +
                "    \"name\": \"Franek\",\n" +
                "    \"surname\": \"Kimono\",\n" +
                "    \"phoneNumber\": \"445666768\",\n" +
                "    \"personalID\": \"123452331\"\n" +
                "}";

        try {
            mvc.perform(post("/api/auth/signup").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Error: Email is already in use!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signInInvalid(){
        String json = "{\n" +
                "\"username\": \"testuser\",\n" +
                "\"password\": \"ytrewq\"\n" +
                "}";

        try {
            mvc.perform(post("/api/auth/signin").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isUnauthorized())
                    .andExpect(content().string(containsString("")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signIn(){
        String json = "{\n" +
                "\"username\": \"szef\",\n" +
                "\"password\": \"qwerty\"\n" +
                "}";

        try {
            mvc.perform(post("/api/auth/signin").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Bearer")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("testuser2")
    public void changePasswordInvalid(){
        String json ="{\n" +
                "\"userId\": 5,\n" +
                "\"oldPassword\": \"invalid\",\n" +
                "\"newPassword\": \"qwerty\"\n" +
                "}";

        try {
            mvc.perform(post("/api/auth/changePass").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithUserDetails("passchanger")
    public void changePassword(){
        String json = "{\n" +
                "\"userId\": 94,\n" +
                "\"oldPassword\": \"qwerty\",\n" +
                "\"newPassword\": \"qwertyyy\"\n" +
                "}";

        try {
            mvc.perform(post("/api/auth/changePass").contentType("application/json")
                    .content(json).header("Authorization", utils.getUserToken("passchanger", "qwerty"))
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("Password changed successfully!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
