package com.AirlinesApp.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "className", nullable = false)
    @NotNull(message = "class name cannot be null")
    private String className;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be null")
    private Long price;

    @Column(name = "paid", nullable = false)
    @NotNull(message = "Paid flag cannot be null")
    private boolean paid;

    @Column(name = "seat", nullable = true)
    private Integer seat;

    @ManyToOne
    @JoinColumn(name = "clientID", referencedColumnName = "id")
    private Client clientID;

    @ManyToOne
    @JoinColumn(name ="flightID", referencedColumnName = "id")
    private Flight flightID;

    public Ticket(){}

    public Ticket(String className, Client clientID, Flight flightID, boolean paid, Integer seat){
        this.className = className;
        this.clientID = clientID;
        this.flightID = flightID;
        if(className == "economic")
            this.price = flightID.getPriceEconomic();
        else
            this.price = flightID.getPriceBuisness();
        this.paid = paid;
        this.seat = seat;
    }
}
