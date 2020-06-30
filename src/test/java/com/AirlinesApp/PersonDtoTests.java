package com.AirlinesApp;

import com.AirlinesApp.dto.PersonDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PersonDtoTests {
    private final static Integer ID = 0;
    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String PERSONALID = "personalID";
    private final static String PHONE_NUMBER = "phoneNumber";

    private PersonDto personDto;

    @Test
    public void objectSerializes(){
        this.personDto = new PersonDto(ID, NAME, SURNAME, PERSONALID, PHONE_NUMBER);
        Assert.assertNotNull(personDto);
        Assert.assertEquals(personDto.getName(), NAME);
        Assert.assertEquals(personDto.getSurname(), SURNAME);
        Assert.assertEquals(personDto.getPersonalID(), PERSONALID);
        Assert.assertEquals(personDto.getId(), ID);
        Assert.assertEquals(personDto.getPhoneNumber(), PHONE_NUMBER);
    }
}
