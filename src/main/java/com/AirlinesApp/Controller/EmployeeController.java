package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Employee;
import com.AirlinesApp.Service.EmployeeService;
import com.AirlinesApp.Transformer.EmployeeTransformer;
import com.AirlinesApp.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream().map(EmployeeTransformer::convertToDto).collect(Collectors.toList());
    }
}
