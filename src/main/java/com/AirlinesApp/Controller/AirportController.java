package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Payload.Request.AddAirportRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Transformer.AirportTransformer;
import com.AirlinesApp.dto.AirportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Klasa kontrolera lotnisk, mapowanego pod adresem "/api/airports".
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    AirportRepository repository;



    /**
     * Mapowanie adresu listy lotów.
     * @return lista lotów w formacie DTO.
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportDto> getAirports(){
        List<Airport> airports = repository.getAllAirports();
        return airports.stream().map(AirportTransformer::convertToDto).collect(Collectors.toList());
    }

    /**
     * Mapowanie adresu dodania lotniska. Wymaga roli pracownika
     * @param req Ciało zapytania
     *            String name - nazwa lotniska
     *            String code - kod lotniska (3-5 znaków)
     *            String city - miasto w którym znajduje sie lotnisko     *
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('EMPLOYEE')")
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
