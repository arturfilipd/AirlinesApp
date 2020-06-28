package com.AirlinesApp.Exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Klasa wyjątku autentykacji
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    /**
     * Konstruktor
     * @param msg - wiadomosc
     * @param t - powód wyjątku
     */
    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Konstruktor
     * @param msg - wiadomość
     */
    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}