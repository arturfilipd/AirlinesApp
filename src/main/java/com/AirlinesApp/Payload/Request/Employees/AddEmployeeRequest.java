package com.AirlinesApp.Payload.Request.Employees;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Klasa żądania dodania pracownika
 */
public class AddEmployeeRequest {
    /**
     * Stanowisko
     */
    @NotBlank
    public String position;
    /**
     * Pensja
     */
    @NotBlank
    public Long salary;
    /**
     * Imie
     */
    @NotBlank
    public String name;
    /**
     * Nazwisko
     */
    @NotBlank
    public String surname;
    /**
     * Numer identyfikacyjny
     */
    @NotBlank
    public String personalID;
    /**
     * Numer telefonu
     */
    @NotBlank
    public String phoneNumber;
    /**
     * Email
     */
    @NotBlank
    public String eMail;
    /**
     * Role użytkownicze
     */
    public Set<String> role;

}
