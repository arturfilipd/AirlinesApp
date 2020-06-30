package com.AirlinesApp;


import com.AirlinesApp.Model.Person;
import com.AirlinesApp.dto.ClientDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
public class ClientDtoTests {
    private final static Integer ID = 0;
    private Person PERSON = new Person();
    private ClientDto clientDto = new ClientDto();

    @Test
    public void objectSerializes(){
        this.clientDto = new ClientDto(ID, PERSON);
        Assert.assertTrue(clientDto.equals(clientDto));
        Assert.assertFalse(clientDto.equals(new ClientDto()));
        Assert.assertEquals(this.clientDto.getId(), ID);
        Assert.assertEquals(this.clientDto.getPersonID(), PERSON);
        ClientDto testClient = new ClientDto(1, PERSON);
        this.clientDto.setId(1);
        this.clientDto.setPersonID(PERSON);
        Assert.assertEquals(this.clientDto, testClient);
    }

}
