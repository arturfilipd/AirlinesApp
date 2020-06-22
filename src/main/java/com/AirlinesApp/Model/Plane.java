package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public Plane(){}

    public Plane(String name, Integer seatsInEconomic, Integer seatsInBuisness, Airport airport){
        this.planeName = name;
        this.seatsInBuisness = seatsInBuisness;
        this.seatsInEconomic = seatsInEconomic;
        this.aPID = airport;

    }

}
