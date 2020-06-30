package com.AirlinesApp.dto;


import lombok.Data;
import lombok.Setter;

@Data
public class PersonDto {
    @Setter private Integer id;
    @Setter private String name;
    @Setter private String surname;
    @Setter private String personalID;
    @Setter private String phoneNumber;

    public PersonDto() {}

    public PersonDto(Integer id, String name, String surname, String personalID, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personalID = personalID;
        this.phoneNumber = phoneNumber;
    }
}
