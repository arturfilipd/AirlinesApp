package com.AirlinesApp.Payload.Request;

import javax.validation.constraints.NotBlank;

/**
 * Klasa żądania zalogowania
 */
public class LoginRequest {
    /**
     * Nazwa użytkownika
     */
    @NotBlank
    private String username;
    /**
     * Hasło
     */
    @NotBlank
    private String password;

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
     * Getter hasła
     * @return password - hasło
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
}