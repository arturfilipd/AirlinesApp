package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Service.AirportService;
import com.AirlinesApp.Transformer.AirportTransformer;
import com.AirlinesApp.dto.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class AirportController {
    private final AirportService airportService;
    @GetMapping("/airports")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportDto> getAirports(){
        List<Airport> airports = airportService.getAllAirports();
        return airports.stream().map(AirportTransformer::convertToDto).collect(Collectors.toList());
    }
}
