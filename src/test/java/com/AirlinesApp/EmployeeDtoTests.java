package com.AirlinesApp;
import com.AirlinesApp.Model.Person;
import com.AirlinesApp.dto.ClientDto;
import com.AirlinesApp.dto.EmployeeDto;
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
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeeDtoTests {
    private final static Integer ID = 0;
    private Person PERSON = new Person();
    private final static Long SALARY = 1200l;
    private final static String POSITION = "Employee";

    private EmployeeDto employeeDto = new EmployeeDto();

    @Test
    public void objectSerializes(){
        this.employeeDto = new EmployeeDto(ID, PERSON, SALARY, new Date(), new Date(), POSITION);
        Assert.assertNotNull(this.employeeDto);
        Assert.assertEquals(employeeDto.getPersonID(), PERSON);
        Assert.assertEquals(employeeDto.getSalary(), SALARY);
        Assert.assertEquals(employeeDto.getPosition(), POSITION);
    }
}
