package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium osoby
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    /**
     * Zwraca listę wszsytkich osób
     * @return lista osób
     */
    @Query(value = "Select * from People", nativeQuery = true)
    List<Person> getAllPeople();

    /**
     * Zwraca osobę po ID
     * @param id - id
     * @return Person
     */
    Person findOneById(Integer id);

    /**
     * Zwraca osobę po nazwie
     * @param name - nazwa
     * @return Person
     */
    Person findOneByName(String name);



}
