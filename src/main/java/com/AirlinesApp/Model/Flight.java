package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Flights")
public class Flight {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    private Integer id;

    @Column(name = "priceEconomic", nullable = true)
    @Getter
    private Long priceEconomic;

    @Column(name = "startDate")
    @Getter
    private Date startDate;

    @Column(name = "endDate")
    @Getter
    private Date endDate;

    @Column(name = "priceBuisness", nullable = true)
    @Getter
    private Long priceBuisness;

    @ManyToOne
    @JoinColumn(name = "startAPID", referencedColumnName = "id")
    @Getter
    private Airport sAPID;

    @ManyToOne
    @JoinColumn(name = "endAPID", referencedColumnName = "id")
    @Getter
    private Airport dAPID;

    @ManyToOne
    @JoinColumn(name = "planeID", referencedColumnName = "id")
    @Getter
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
