package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.Payload.Request.Planes.AddPlaneRequest;
import com.AirlinesApp.Payload.Request.Planes.GetPlanesRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Repository.PlaneRepository;
import com.AirlinesApp.Service.PlaneService;
import com.AirlinesApp.Transformer.PlaneTransformer;
import com.AirlinesApp.dto.PlaneDto;
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
 * Klasa kontrolera Samolotów, mapowanego pod adresem "/api/planes".
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/planes")
public class PlaneController{

    @Autowired
    AirportRepository airports;
    @Autowired
    PlaneRepository repository;


    private final PlaneService planeService;

    /**
     * Mapowanie listy samolotów
     * @return Lista samolotów w formacie DTO.
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaneDto> getPlanes(){
        List<Plane> planes = planeService.getAllPlanes();
        return planes.stream().map(PlaneTransformer::convertToDto).collect(Collectors.toList());
    }

    /**
     * Mapowanie listy samolotów na danym lotnisku
     * @param req Ciało zaptania
     *            Integer airportId - ID lotniska
     * @return Lista samolotów na danym lotnisku w formacie DTO.
     */
    @GetMapping("/getByAirport")
    @PreAuthorize("hasRole('EMPLOYEE')")
   public List<PlaneDto> getPlanesAtAirport(@Valid @RequestBody GetPlanesRequest req){
        List<Plane> planes = repository.getAllByaPID(airports.findOneById(req.airportId));
        return planes.stream().map(PlaneTransformer::convertToDto).collect(Collectors.toList());
    }

    /**
     * Mapowanie dodawania samolotu
     * @param req Ciało zapytania
     *            Integer airportId - ID lotniska
     *            Integer businessSeats - liczba miejsc w klasie biznesowej
     *            Integer ecoSeats - liczba miejsc w klasie ekonomicznej
     *            String name - nazwa samolotu
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> addPlane(@Valid @RequestBody AddPlaneRequest req){
        if(!airports.existsById(req.airportId)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid airport!"));
        }
        repository.save(new Plane(req.name, req.ecoSeats, req.businessSeats, airports.findOneById(req.airportId)));
        return ResponseEntity.ok(new MessageResponse("Plane added successfully!"));
    }
}
