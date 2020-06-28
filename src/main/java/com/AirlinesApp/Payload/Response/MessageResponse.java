package com.AirlinesApp.Payload.Response;

/**
 * Klasa wiadomości odpowiedzi
 */
public class MessageResponse {
    private String message;

    /**
     * Konstruktor
     * @param message - wiadomość
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Getter
     * @return message - wiadomość
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter
     * @param message - wiadomość
     */
    public void setMessage(String message) {
        this.message = message;
    }
}