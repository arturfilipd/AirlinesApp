package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "Select * from Employees", nativeQuery = true)
    List<Employee> getAllEmployees();
}
