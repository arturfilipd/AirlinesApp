package com.AirlinesApp.Payload.Request.Flights;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Klasa żądania dodania lotu
 */
public class AddFlightRequest {
    /**
     * Data wylotu
     */
    @NotBlank
    @DateTimeFormat
    public Date starts;
    /**
     * Data przylotu
     */
    @NotBlank
    @DateTimeFormat
    public Date ends;
    /**
     * ID lotniska docelowego
     */
    @NotBlank
    public Integer destID;
    /**
     * ID lotniska początkowego
     */
    @NotBlank
    public Integer sourceID;
    /**
     * ID samolotu
     */
    @NotBlank
    public Integer planeID;
    /**
     * Cena klasy ekonomicznej
     */
    @NotBlank
    public Long priceEco;
    /**
     * Cena klasy biznesowej
     */
    @NotBlank
    public Long priceBusi;



}