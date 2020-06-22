package com.AirlinesApp.Payload.Request.Tickets;

/**
 * Klasa żądania dodania biletu
 */
public class AddTicketRequest {
    /**
     * ID lotu
     */
    public Integer flightId;
    /**
     * ID użytkownika
     */
    public Long userId;
    /**
     * Klasa sekcji w samolocie
     */
    public String ticketClass;
    /**
     * Flaga opłaty biletu
     */
    public boolean paid;
}
