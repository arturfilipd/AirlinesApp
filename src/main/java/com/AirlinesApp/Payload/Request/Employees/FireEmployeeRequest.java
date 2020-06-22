package com.AirlinesApp.Payload.Request.Employees;

import javax.validation.constraints.NotBlank;

public class FireEmployeeRequest {
    @NotBlank
    public Integer id;
}
