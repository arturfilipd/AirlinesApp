package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Airport;
import lombok.Data;
import lombok.Setter;

@Data
public class PlaneDto {
    @Setter private Integer id;
    @Setter private String planeName;
    @Setter private Integer seatsInEconomic;
    @Setter private Integer seatsInBuisness;
    @Setter private Airport aPID;
}
