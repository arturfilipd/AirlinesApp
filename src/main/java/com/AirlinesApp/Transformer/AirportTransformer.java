package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.dto.AirportDto;
import org.springframework.beans.BeanUtils;

public class AirportTransformer {
    public static AirportDto convertToDto(Airport airport){
        AirportDto airportDto = new AirportDto();
        BeanUtils.copyProperties(airport, airportDto);
        return airportDto;
    }
}
