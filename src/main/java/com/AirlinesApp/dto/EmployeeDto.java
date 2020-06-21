package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Person;
import lombok.Data;


import java.util.Date;
@Data
public class EmployeeDto {
    private Integer id;
    private Person personID;
    private Long salary;
    private Date hiringDate;
    private Date firingDate;
    private String position;
}
