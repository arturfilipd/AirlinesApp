package com.AirlinesApp.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Flights")
public class Flight {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "priceEconomic", nullable = true)
    private Long priceEconomic;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

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

    public Flight(){}

    public Flight(Date starts, Date ends, Airport source, Airport destination, Long eco, Long buis, Plane plane){
        this.startDate = starts;
        this.endDate = ends;
        this.dAPID = destination;
        this.sAPID = source;
        this.priceEconomic = eco;
        this.priceBuisness = buis;
        this.planeID = plane;
    }

}
