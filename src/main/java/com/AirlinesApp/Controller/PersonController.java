package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Service.PersonService;
import com.AirlinesApp.Transformer.PersonTransformer;
import com.AirlinesApp.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PersonController{
    private final PersonService personService;
    @GetMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersons(){
        List<Person> people = personService.getAllPeople();
        return people.stream().map(PersonTransformer::convertToDto).collect(Collectors.toList());
    }
}