package com.AirlinesApp.Payload.Response;

import lombok.Getter;
import lombok.Setter;

/**
 * Klasa odpowiedzi dla nowego pracownika
 */
public class NewEmployeeResponse {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;

    /**
     * Konstruktor
     * @param l - login
     * @param p - hasło
     */
    public NewEmployeeResponse(String l, String p) {
        this.login = l;
        this.password = p;
    }

}