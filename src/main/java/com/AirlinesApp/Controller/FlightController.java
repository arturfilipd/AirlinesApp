package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.Service.FlightService;
import com.AirlinesApp.Transformer.FlightTransformer;
import com.AirlinesApp.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class FlightController{
    private final FlightService flightService;
    @GetMapping("/flights")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDto> getFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return flights.stream().map(FlightTransformer::convertToDto).collect(Collectors.toList());
    }
}
