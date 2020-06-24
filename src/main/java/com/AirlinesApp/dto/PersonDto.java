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
}
