package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

/**
 * Klasa modelująca lot
 */
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

    /**
     * Getter samolotu
     * @return planeID - samolot
     */
    public Plane getPlaneID(){
        return planeID;
    }

    /**
     * Konstruktor domyślny
     */
    public Flight(){}

    /**
     * Konstruktor
     * @param starts - data startu
     * @param ends - data lądowania
     * @param source - lotnisko startu
     * @param destination - lotnisko docelowe
     * @param eco - cena za klasę ekonomiczną
     * @param buis - cena za klasę biznesową
     * @param plane - samolot
     */
    public Flight(Date starts, Date ends, Airport source, Airport destination, Long eco, Long buis, Plane plane){
        this.startDate = starts;
        this.endDate = ends;
        this.dAPID = destination;
        this.sAPID = source;
        this.priceEconomic = eco;
        this.priceBuisness = buis;
        this.planeID = plane;
    }

    /**
     * toString
     * @return toString
     */
    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", priceEconomic=" + priceEconomic +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceBuisness=" + priceBuisness +
                ", sAPID=" + sAPID +
                ", dAPID=" + dAPID +
                ", planeID=" + planeID +
                '}';
    }
}
