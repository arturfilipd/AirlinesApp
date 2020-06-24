package com.AirlinesApp;

import com.AirlinesApp.Model.Employee;
import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Repository.EmployeeRepository;
import com.AirlinesApp.Repository.PersonRepository;
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
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeeControllerTests {

    @Autowired
    EmployeeRepository employees;
    @Autowired
    PersonRepository people;

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
    public void addEmployeeTest(){

        String json = "{\n" +
                "\"position\": \"employee\",\n" +
                "\"salary\": 1300,\n" +
                "\"name\": \"TestEmployee\",\n" +
                "\"surname\": \"Brzeczystrzykiewicz\",\n" +
                "\"personalID\": \"123245\",\n" +
                "\"phoneNumber\": \"2325435\",\n" +
                "\"eMail\": \"typiarz2000@gmail.com\",\n" +
                "\"role\": [\"employee\", \"user\"]\n" +
                "}";

        try {
            mvc.perform(post("/api/employees/add").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Person personID = people.findOneByName("TestEmployee");
        Employee employee = employees.findOneByPersonID(personID);
        Integer id = employee.getId();
        json = "{\n" +
                "\"id\":" + id + "\n" +
                "}";
        try {
            mvc.perform(post("/api/employees/fire").contentType("application/json")
                    .content(json)
            ).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            e.printStackTrace();
        }





    } //addEmployeeTest

    @Test
    @WithUserDetails("szef")
    public void fireEmployeeTest(){

    }
}
