package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Employee;
import com.AirlinesApp.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repozytorium pracownika
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /**
     * Zwraca wszystkich pracowników
     * @return Lista pracowników
     */
    @Query(value = "Select * from Employees", nativeQuery = true)
    List<Employee> getAllEmployees();

    /**
     * Ustawia datę zwolnienia, zwalniając pracownika
     * @param fDate - data zwolnienia
     * @param id - id
     */
    @Modifying
    @Query(value = "Update Employee set firingDate = :fDate where id = :id")
    void removeEmployee(@Param("fDate")Date fDate, @Param("id")Integer id);

    /**
     * Sprawdza czy pracownik istnieje po id osoby
     * @param personID - id osoby
     * @return Boolean
     */
    Boolean existsByPersonID(Integer personID);

    /**
     * Zwraca pracownika po osobie
     * @param personID - osoba
     * @return Employee
     */
    Employee findOneByPersonID(Person personID);
}
