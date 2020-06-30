package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Person;
import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private Person personID;
    public ClientDto(){}
    public ClientDto(Integer id, Person personID) {
        this.id = id;
        this.personID = personID;
    }
}
