package com.AirlinesApp.Payload.Request.Flights;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Klasa żądania edycji lotu
 */
public class EditFlightRequest {
    /**
     * ID lotu
     */
    @NotBlank
    public Integer id;
    /**
     * Data wylotu
     */
    @NotBlank
    @DateTimeFormat
    public Date newStart;
    /**
     * Data przylotu
     */
    @NotBlank
    @DateTimeFormat
    public Date newEnd;
}
