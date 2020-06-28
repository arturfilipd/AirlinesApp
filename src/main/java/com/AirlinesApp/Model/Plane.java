package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasa modelująca samolot
 */
@Entity
@Table(name = "Planes")
public class Plane {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Integer id;

    @Column(name = "planeName", nullable = false)
    @NotNull(message = "plane name cannot be null")
    @Getter
    private String planeName;

    @Column(name = "seatsInEconomic", nullable = true)
    @Getter private Integer seatsInEconomic;

    @Column(name = "seatsInBuisness", nullable = true)
    @Getter private Integer seatsInBuisness;

    @ManyToOne
    @JoinColumn(name = "aPID", referencedColumnName = "id")
    @Getter private Airport aPID;

    /**
     * Konstruktor domyślny
     */
    public Plane(){}

    /**
     * Getter miejsc ekonomicznych
     * @return seatsInEconomic - miejsca w klasie ekonomicznej
     */
    public Integer getSeatsInEconomic() {
        return seatsInEconomic;
    }

    /**
     * Getter miejsc biznesowych
     * @return seatsInBuisness - miejsca w klasie biznesowej
     */
    public Integer getSeatsInBuisness() {
        return seatsInBuisness;
    }

    /**
     * Konstruktor
     * @param name - nazwa
     * @param seatsInEconomic - miejsca w klasie ekonomicznej
     * @param seatsInBuisness - miejsca w klasie biznesowej
     * @param airport - lotnisko
     */
    public Plane(String name, Integer seatsInEconomic, Integer seatsInBuisness, Airport airport){
        this.planeName = name;
        this.seatsInBuisness = seatsInBuisness;
        this.seatsInEconomic = seatsInEconomic;
        this.aPID = airport;

    }

}
