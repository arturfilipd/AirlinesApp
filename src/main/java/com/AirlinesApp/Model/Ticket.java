package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasa modelująca bilet
 */
@Entity
@Table(name = "Tickets")
public class Ticket {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

    @Column(name = "className", nullable = false)
    @NotNull(message = "class name cannot be null")
    @Getter
    private String className;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be null")
    @Getter
    private Long price;

    @Column(name = "paid", nullable = false)
    @NotNull(message = "Paid flag cannot be null")
    @Getter
    private boolean paid;

    @Column(name = "seat", nullable = true)
    @Getter
    private Integer seat;

    @ManyToOne
    @JoinColumn(name = "clientID", referencedColumnName = "id")
    @Getter private Client clientID;

    @ManyToOne
    @JoinColumn(name ="flightID", referencedColumnName = "id")
    @Getter
    private Flight flightID;

    /**
     * Getter flagi zapłaty
     * @return paid - flaga
     */
    public boolean getPaid(){
        return paid;
    }

    /**
     * Getter miejsca
     * @return seat - miejsce
     */
    public Integer getSeat() {
        return seat;
    }

    /**
     * Konstruktor domyślny
     */
    public Ticket(){}

    /**
     * Konstruktor
     * @param className - nazwa klasy
     * @param clientID - ID klienta
     * @param flightID - ID lotu
     * @param paid - flaga zapłaty
     * @param seat - miejsce
     */
    public Ticket(String className, Client clientID, Flight flightID, boolean paid, Integer seat){
        this.className = className;
        this.clientID = clientID;
        this.flightID = flightID;
        if(className.equals("Economic"))
            this.price = flightID.getPriceEconomic();
        else
            this.price = flightID.getPriceBuisness();
        this.paid = paid;
        this.seat = seat;
    }
}
