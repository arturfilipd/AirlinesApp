package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.dto.AirportDto;
import org.springframework.beans.BeanUtils;

/**
 * Klasa transformera lotniska
 */
public class AirportTransformer {
    public static AirportDto convertToDto(Airport airport){
        AirportDto airportDto = new AirportDto();
        BeanUtils.copyProperties(airport, airportDto);
        return airportDto;
    }

    public static Airport convertToEntity(AirportDto airportDto){
        Airport airport = new Airport();
        BeanUtils.copyProperties(airportDto, airport);
        return airport;
    }
}
