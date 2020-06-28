package com.AirlinesApp.Payload.Request.Flights;

import com.AirlinesApp.Model.Flight;

import javax.validation.constraints.NotBlank;

/**
 * Klasa żądania uzyskania wolnych miejsc w locie
 */
public class GetFreeSeatsRequest {
    /**
     * ID lotu
     */
    @NotBlank
    public Integer flightId;
    /**
     * Nazwa klasy
     */
    @NotBlank
    public String className;
}

