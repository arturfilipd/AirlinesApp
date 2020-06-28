package com.AirlinesApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Klasa wyjątku BadRequest
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * Konstruktor
     * @param message - wiadomość
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Konstruktor
     * @param message - wiadomość
     * @param cause - powód wyjątku
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}