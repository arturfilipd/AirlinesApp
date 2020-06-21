package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Repository.PersonRepository;
import com.AirlinesApp.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    public List<Person> getAllPeople(){
        return personRepository.getAllPeople();
    }
}
