package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Employee;
import com.AirlinesApp.dto.EmployeeDto;
import org.springframework.beans.BeanUtils;

public class EmployeeTransformer{
    public static EmployeeDto convertToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        return employeeDto;
    }
}
