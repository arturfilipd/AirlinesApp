package com.AirlinesApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "Flights")
public class Flight {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "priceEconomic", nullable = true)
    private Long priceEconomic;

    @Column(name = "priceBuisness", nullable = true)
    private Long priceBuisness;

    @ManyToOne
    @JoinColumn(name = "startAPID", referencedColumnName = "id")
    private Airport sAPID;

    @ManyToOne
    @JoinColumn(name = "endAPID", referencedColumnName = "id")
    private Airport dAPID;

    @ManyToOne
    @JoinColumn(name = "planeID", referencedColumnName = "id")
    private Plane planeID;


}
