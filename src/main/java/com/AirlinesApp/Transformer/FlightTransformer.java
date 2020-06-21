package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.dto.FlightDto;
import org.springframework.beans.BeanUtils;

public class FlightTransformer {
    public static FlightDto convertToDto(Flight flight){
        FlightDto flightDto = new FlightDto();
        BeanUtils.copyProperties(flight, flightDto);
        return flightDto;
    }
    public static Flight convertToEntity(FlightDto flightDto){
        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDto, flight);
        return flight;
    }
}
