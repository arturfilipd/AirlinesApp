package com.AirlinesApp.Payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Klasa żądania rejestracji
 */
public class SignupRequest {
    /**
     * Nazwa
     */
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    /**
     * Email
     */
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    /**
     * Role użytkownicze
     */
    private Set<String> role;
    /**
     * Hasło
     */
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    /**
     * Imie
     */
    public String name;
    /**
     * Nazwisko
     */
    public String surname;
    /**
     * Numer identyfikacyjny
     */
    public String personalID;
    /**
     * Numer telefonu
     */
    public String phoneNumber;

    /**
     * Getter nazwy
     * @return username - nazwa
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter nazwy
     * @param username - nazwa
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter emailu
     * @return email - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter emailu
     * @param email - email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter hasła
     * @return password- hasło
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter hasła
     * @param password - hasło
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter ról użytkowniczych
     * @return role - rola
     */
    public Set<String> getRole() {
        return this.role;
    }

    /**
     * Setter ról użytkowniczych
     * @param role - rola
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}