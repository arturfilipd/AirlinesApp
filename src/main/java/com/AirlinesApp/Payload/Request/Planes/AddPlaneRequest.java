package com.AirlinesApp.Payload.Request.Planes;

/**
 * Klasa żądania dodania samolotu
 */
public class AddPlaneRequest {
    /**
     * ID lotniska
     */
    public Integer airportId;
    /**
     * Ilość miejscu w klasie biznes
     */
    public Integer businessSeats;
    /**
     * Ilość miejsc w klasie ekonomicznej
     */
    public Integer ecoSeats;
    /**
     * Nazwa samolotu
     */
    public String name;
}
