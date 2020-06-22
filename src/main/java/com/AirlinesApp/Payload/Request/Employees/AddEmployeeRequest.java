package com.AirlinesApp.Payload.Request.Employees;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class AddEmployeeRequest {
    @NotBlank
    public String position;
    @NotBlank
    public Long salary;
    @NotBlank
    public String name;
    @NotBlank
    public String surname;
    @NotBlank
    public String personalID;
    @NotBlank
    public String phoneNumber;
    @NotBlank
    public String eMail;
    public Set<String> role;

}
