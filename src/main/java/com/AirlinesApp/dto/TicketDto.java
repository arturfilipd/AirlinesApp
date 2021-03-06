package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Model.Flight;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TicketDto {
    @Getter @Setter private Integer id;
    @Getter @Setter private String className;
    @Getter @Setter private Long price;
    @Setter private Integer seat;
    @Setter private boolean paid;
    @Getter @Setter private Client clientID;
    @Getter @Setter private Flight flightID;

    public TicketDto() {}

    public TicketDto(Integer id, String className, Long price, Integer seat, boolean paid, Client clientID, Flight flightID) {
        this.id = id;
        this.className = className;
        this.price = price;
        this.seat = seat;
        this.paid = paid;
        this.clientID = clientID;
        this.flightID = flightID;
    }
}
