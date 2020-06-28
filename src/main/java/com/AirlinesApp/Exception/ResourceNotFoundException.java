package com.AirlinesApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Klasa wyjątku nie znalezienia zasobu
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    /**
     * Konstruktor
     * @param resourceName - nazwa zasobu
     * @param fieldName - nazwa pola
     * @param fieldValue - wartość pola
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     * Getter nazwy zasobu
     * @return resourceName - nazwa zasobu
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Getter nazwy pola
     * @return fieldName - nazwa pola
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Getter wartości pola
     * @return fieldValue - wartość pola
     */
    public Object getFieldValue() {
        return fieldValue;
    }
}