package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.Payload.Request.AddFlightRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Repository.FlightRepository;
import com.AirlinesApp.Service.FlightService;
import com.AirlinesApp.Transformer.FlightTransformer;
import com.AirlinesApp.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController{

    @Autowired
    FlightRepository repository;

    @Autowired
    AirportRepository airports;

    private final FlightService flightService;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDto> getFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return flights.stream().map(FlightTransformer::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<?>addFlight(AddFlightRequest req){
        if(!airports.existsById(req.sourceID)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid Source Airport!"));
        }

        if(!airports.existsById(req.destID)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid Destination Airport!"));
        }
        Airport src = airports.findOneById(req.sourceID);
        Airport dest = airports.findOneById(req.destID);

        repository.save(new Flight(req.starts, req.ends, src, dest, req.priceEco, req.priceBuis));
        return ResponseEntity.ok(new MessageResponse("Flight added successfully!"));
    }
}
