package com.AirlinesApp.Payload.Request.Employees;

import javax.validation.constraints.NotBlank;

/**
 * Klasa żądania zwolnienia pracownika
 */
public class FireEmployeeRequest {
    /**
     * ID pracownika
     */
    @NotBlank
    public Integer id;
}
