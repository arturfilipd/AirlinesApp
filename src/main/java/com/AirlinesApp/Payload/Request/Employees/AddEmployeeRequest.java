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

    /**
     * Konstruktor
     * @param position - stanowisko
     * @param salary - pensja
     * @param name - imie
     * @param surname - nazwisko
     * @param personalID - osobisty numer identyfikacyjny  (np pesel)
     * @param phoneNumber - numer telefonu
     * @param eMail - email
     * @param role - role użytkownicze
     */
    public AddEmployeeRequest(String position, Long salary, String name, String surname, String personalID, String phoneNumber, String eMail, Set<String> role){
        this.position = position;
        this.salary = salary;
        this.name = name;
        this.surname = surname;
        this.personalID = personalID;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.role = role;

    }

}
