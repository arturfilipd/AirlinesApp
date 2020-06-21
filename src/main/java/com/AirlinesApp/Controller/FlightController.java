package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.Payload.Request.DeleteRequest;
import com.AirlinesApp.Payload.Request.Flights.AddFlightRequest;
import com.AirlinesApp.Payload.Request.Flights.EditFlightRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Repository.FlightRepository;
import com.AirlinesApp.Repository.PlaneRepository;
import com.AirlinesApp.Service.FlightService;
import com.AirlinesApp.Transformer.FlightTransformer;
import com.AirlinesApp.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController{

    @Autowired
    FlightRepository repository;

    @Autowired
    AirportRepository airports;

    @Autowired
    PlaneRepository planes;

    private final FlightService flightService;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDto> getFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return flights.stream().map(FlightTransformer::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?>addFlight(@Valid @RequestBody AddFlightRequest req){

        if (!airports.existsById(req.sourceID)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid source airport!"));
        }

        if(!airports.existsById(req.destID)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid destination airport!"));
        }


        Airport src = airports.findOneById(req.sourceID);
        Airport dest = airports.findOneById(req.destID);
        Plane p = planes.findOneById(req.planeID);

        repository.save(new Flight(req.starts, req.ends, src, dest, req.priceEco, req.priceBuis, p));
        return ResponseEntity.ok(new MessageResponse("Flight added successfully!"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> deleteFlight(@Valid @RequestBody DeleteRequest req){
        if (!repository.existsById(req.id)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No such flight!"));
        }
        repository.deleteById(req.id);
        return ResponseEntity.ok(new MessageResponse("Flight removed successfully!"));
    }

    @PostMapping("/update")
    @Transactional
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateFlight(@Valid @RequestBody EditFlightRequest req){
        if (!repository.existsById(req.id)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No such flight!"));
        }
        if(req.newStart == null || req.newEnd == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid date!"));
        }
        repository.update(req.id, req.newStart, req.newEnd);
        return ResponseEntity.ok(new MessageResponse("Flight edited successfully!"));
    }

}
