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

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "Select * from Employees", nativeQuery = true)
    List<Employee> getAllEmployees();

    @Modifying
    @Query(value = "Update Employee set firingDate = :fDate where id = :id")
    void removeEmployee(@Param("fDate")Date fDate, @Param("id")Integer id);
    Boolean existsByPersonID(Integer personID);
    Employee findOneByPersonID(Person personID);
}
