package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.Model.Ticket;
import com.AirlinesApp.Payload.Request.DeleteRequest;
import com.AirlinesApp.Payload.Request.Flights.AddFlightRequest;
import com.AirlinesApp.Payload.Request.Flights.EditFlightRequest;
import com.AirlinesApp.Payload.Request.Flights.GetFreeSeatsRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Repository.FlightRepository;
import com.AirlinesApp.Repository.PlaneRepository;
import com.AirlinesApp.Repository.TicketRepository;
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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Klasa kontrolera lotów, mapowanego pod adresem "/api/flights".
 */
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

    @Autowired
    TicketRepository tickets;


    /**
     * Mapowanie listy lotów
     * @return Lista lotów w formacie DTO
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDto> getFlights(){
        List<Flight> flights = repository.getAllFlights();
        return flights.stream().map(FlightTransformer::convertToDto).collect(Collectors.toList());
    }

    /**
     * Mapowanie dodawania nowego lotu
     * @param req Ciało zapytania
     *      Date starts - data startu lotu
     *      Date ends - data końca lotu
     *      Integer sourceID - ID lotniska początkowego
     *      Integer destID - ID lotniska końcowego
     *      Integer planeID - ID samolotu
     *      Long priceEco - cena biletu klasy ekonomicznej
     *      Long priceBusi - cena biletu klase biznesowej
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?>addFlight(@Valid @RequestBody AddFlightRequest req){

        if (!airports.existsById(req.sourceID)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid source airport!"));
        }

        if(!planes.existsById(req.planeID)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid plane id!"));
        }

        if(!airports.existsById(req.destID)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid destination airport!"));
        }


        Airport src = airports.findOneById(req.sourceID);
        Airport dest = airports.findOneById(req.destID);
        Plane p = planes.findOneById(req.planeID);

        repository.save(new Flight(req.starts, req.ends, src, dest, req.priceEco, req.priceBusi, p));
        return ResponseEntity.ok(new MessageResponse("Flight added successfully!"));
    }

    /**
     * Mapowanie usuwania lotu
     * @param req Ciało zapytania
     *            Integer id - ID lotu
     * @return Odpowiedź informująca o rezultacie działania.
     */
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

    /**
     * Mapowanie edycji lotu
     * @param req Ciało zapytania
     *            Integer id - ID lotu
     *            Date newStart - nowa data startu lotu
     *            Date newEnd - nowa data końca lotu
     * @return Odpowiedź informująca o rezultacie działania.
     */
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

    /**
     * Mapowanie gettera wolnych miejsc w locie
     * @param req Ciało zapytania
     *            Integer flightId - ID lotu
     *            String className - nazwa klasy miejsc
     * @return Lista wolnych miejsc
     */
    @PostMapping("/getFreeSeats")
    public List<Integer> getFreeSeats(@RequestBody GetFreeSeatsRequest req){
        if(repository.existsById(req.flightId)) {
            Flight flight = repository.findOneById(req.flightId);
            List<Ticket> tickets = this.tickets.findAllByFlightID(flight);
            List<Integer> freeSeats = new LinkedList<>();
            int seats = 0;
            if (req.className.equals("Economic"))
                seats = flight.getPlaneID().getSeatsInEconomic();
            else
                seats = flight.getPlaneID().getSeatsInBuisness();
            boolean free = true;
            for (int i = 0; i < seats; i++) {
                free = true;
                for (int j = 0; j < tickets.size(); j++) {
                    if (tickets.get(j).getSeat()!=null && i == tickets.get(j).getSeat())
                        free = false;
                }
                if (free == true) freeSeats.add(i);
            }
            return freeSeats;
        }
        List<Integer> responseList = new LinkedList<>();
        responseList.add(-1);
        return responseList;
        }
}
