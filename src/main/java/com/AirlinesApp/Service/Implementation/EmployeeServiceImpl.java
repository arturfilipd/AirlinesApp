package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Employee;
import com.AirlinesApp.Repository.EmployeeRepository;
import com.AirlinesApp.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }
}
