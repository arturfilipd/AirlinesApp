package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Plane;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class FlightDto {
    @Getter @Setter private Integer id;
    @Getter @Setter private Long priceEconomic;
    @Getter @Setter private Date startDate;
    @Getter @Setter private Date endDate;
    @Getter @Setter private Long priceBuisness;
    @Getter @Setter private Airport sAPID;
    @Getter @Setter private Airport dAPID;
    @Getter @Setter private Plane planeID;

    public FlightDto() {}

    public FlightDto(Integer id, Long priceEconomic, Date startDate, Date endDate, Long priceBuisness, Airport sAPID, Airport dAPID, Plane planeID) {
        this.id = id;
        this.priceEconomic = priceEconomic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceBuisness = priceBuisness;
        this.sAPID = sAPID;
        this.dAPID = dAPID;
        this.planeID = planeID;
    }
}
