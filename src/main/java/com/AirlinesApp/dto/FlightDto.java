package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Plane;
import lombok.Data;

@Data
public class FlightDto {
    private Integer id;
    private Long priceEconomic;
    private Long priceBuisness;
    private Airport sAPID;
    private Airport dAPID;
    private Plane planeID;
}
