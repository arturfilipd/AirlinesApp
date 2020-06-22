package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
@Data
public class EmployeeDto {
    @Getter @Setter private Integer id;
    @Getter @Setter private Person personID;
    @Getter @Setter private Long salary;
    @Getter @Setter private Date hiringDate;
    @Getter @Setter private Date firingDate;
    @Getter @Setter private String position;
}
