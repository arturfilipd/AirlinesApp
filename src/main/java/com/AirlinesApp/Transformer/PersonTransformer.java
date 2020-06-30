package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.dto.PersonDto;
import org.springframework.beans.BeanUtils;

public class PersonTransformer {
    public static PersonDto convertToDto(Person person){
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(person, personDto);
        return personDto;
    }
}
