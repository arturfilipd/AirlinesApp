package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query(value = "Select * from People", nativeQuery = true)
    List<Person> getAllPeople();
    Person findOneById(Integer id);


}
