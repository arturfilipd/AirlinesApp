package com.AirlinesApp.Payload.Response;

import java.util.List;

/**
 * Klasa odpowiedzi autoryzacji
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    /**
     * Konstruktor
     * @param accessToken - Token
     * @param id - id
     * @param username - nazwa użytkownika
     * @param email - eMail
     * @param roles - role użytkownicze
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * Getter
     * @return token - token
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * Setter
     * @param accessToken - token
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * Getter
     * @return type - typ
     */
    public String getTokenType() {
        return type;
    }

    /**
     * Setter
     * @param tokenType - typ
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * Getter
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter
     * @param id - id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter
     * @return email - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter
     * @param email - email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter
     * @return username - nazwa użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter
     * @param username - nazwa użytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter
     * @return roles - role
     */
    public List<String> getRoles() {
        return roles;
    }
}