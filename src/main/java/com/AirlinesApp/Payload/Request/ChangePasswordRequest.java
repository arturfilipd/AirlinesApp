package com.AirlinesApp.Payload.Request;

/**
 * Klasa żądania zmiany hasła
 */
public class ChangePasswordRequest {
    /**
     * ID użytkownika
     */
    public Long userId;
    /**
     * Dotychczasowe hasło
     */
    public String oldPassword;
    /**
     * Nowe hasło
     */
    public String newPassword;
}
