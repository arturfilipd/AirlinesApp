package com.AirlinesApp;

import com.AirlinesApp.Controller.EmployeeController;
import com.AirlinesApp.Payload.Request.Employees.AddEmployeeRequest;
import com.AirlinesApp.dto.AirportDto;
import com.AirlinesApp.dto.EmployeeDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
public class EmployeeControllerTests {
    @Autowired
    EmployeeController employeeController;

    /*@Test
    void addEmployeeTest(){
        Set<String> set = new HashSet<String>(); set.add("user"); set.add("employee");
        ResponseEntity response = employeeController.addEmployee(new AddEmployeeRequest("Employee", 2000l, "Test", "Test", "11111111111", "111111111", "test@test.test", set));
        String expectedResponse = "Employee added successfully!";
        Assert.assertEquals(expectedResponse, response.getBody());
    }*/

}
