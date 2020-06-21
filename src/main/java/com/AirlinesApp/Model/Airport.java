package com.AirlinesApp.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Airports")
public class Airport {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "airportName", nullable = false)
    @NotNull(message = "Airport name cannot be null")
    private String airportName;

    @Column(name = "cityCode", nullable = false)
    @NotNull(message = "City code cannot be null")
    private String cityCode;

    @Column(name = "cityName", nullable = false)
    @NotNull(message = "City name cannot be null")
    private String cityName;


}
