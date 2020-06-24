package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Airports")
public class Airport {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    private Integer id;

    @Column(name = "airportName", nullable = false)
    @NotNull(message = "Airport name cannot be null")
    @Getter
    private String airportName;

    @Column(name = "code", nullable = false)
    @NotNull(message = "Code cannot be null")
    @Getter
    private String code;

    @Column(name = "cityName", nullable = false)
    @NotNull(message = "City name cannot be null")
    @Getter
    private String cityName;

    public Airport (String name, String code, String cityName){
        this.airportName = name;
        this.code = code;
        this.cityName = cityName;
    }

    public Airport (){};

    public Integer getId(){
        return id;
    }
    public String getName() {return airportName;}

}
