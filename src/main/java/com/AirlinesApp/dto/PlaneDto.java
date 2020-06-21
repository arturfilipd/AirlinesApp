package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Airport;
import lombok.Data;

@Data
public class PlaneDto {
    private Integer id;
    private String planeName;
    private Integer seatsInEconomic;
    private Integer seatsInBuisness;
    private Airport aPID;
}
