package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Repository.PersonRepository;
import com.AirlinesApp.Transformer.PersonTransformer;
import com.AirlinesApp.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa kontrolera osób, mapowanego pod adresem "/api/people"
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PersonController{

    @Autowired
    PersonRepository repository;

    /**
     * Mapowanie gettera wszystkich pracowników
     * @return lista pracowników w formacie DTO
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getEmployees() {
        List<Person> people = repository.getAllPeople();
        return people.stream().map(PersonTransformer::convertToDto).collect(Collectors.toList());
    }
}