package com.AirlinesApp.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Planes")
public class Plane {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "planeName", nullable = false)
    @NotNull(message = "plane name cannot be null")
    private String planeName;

    @Column(name = "seatsInEconomic", nullable = true)
    private Integer seatsInEconomic;

    @Column(name = "seatsInBuisness", nullable = true)
    private Integer seatsInBuisness;

    @ManyToOne
    @JoinColumn(name = "aPID", referencedColumnName = "id")
    private Airport aPID;
}
