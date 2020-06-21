package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Payload.Request.AddAirportRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Service.AirportService;
import com.AirlinesApp.Transformer.AirportTransformer;
import com.AirlinesApp.dto.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    AirportRepository repository;

    private final AirportService airportService;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportDto> getAirports(){
        List<Airport> airports = airportService.getAllAirports();
        return airports.stream().map(AirportTransformer::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/add")
    public ResponseEntity<?> addAirport(@Valid @RequestBody AddAirportRequest req){
        if (repository.existsByCode(req.getCode())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Code is already taken!"));
        }

        repository.save(new Airport(req.getName(), req.getCode(), req.getCity()));
        return ResponseEntity.ok(new MessageResponse("Airport added successfully!"));
    }
}
