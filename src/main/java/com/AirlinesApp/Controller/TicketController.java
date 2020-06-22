package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Ticket;
import com.AirlinesApp.Model.User;
import com.AirlinesApp.Payload.Request.Tickets.AddTicketRequest;
import com.AirlinesApp.Payload.Request.Tickets.CheckInTicketRequest;
import com.AirlinesApp.Payload.Request.Tickets.DeleteTicketRequest;
import com.AirlinesApp.Payload.Request.Tickets.GetTicketsRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.ClientRepository;
import com.AirlinesApp.Repository.FlightRepository;
import com.AirlinesApp.Repository.TicketRepository;
import com.AirlinesApp.Repository.UserRepository;
import com.AirlinesApp.Transformer.TicketTransformer;
import com.AirlinesApp.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa kontrolera biletów, mapowanego pod adresem "/api/tickets".
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired TicketRepository repository;
    @Autowired UserRepository users;
    @Autowired ClientRepository clients;
    @Autowired FlightRepository flights;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getTickets(){
        List<Ticket> tickets = repository.getAllTickets();
        return tickets.stream().map(TicketTransformer::convertToDto).collect(Collectors.toList());
    }

    /**
     * Mapowanie wyswietlania biletow danego klienta
     * @param req Cialo zapytania
     *            Integer userID - ID usera
     * @return Lista biletow
     */
    @PostMapping("/listByClientID")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getTicketsByClientId(@Valid @RequestBody GetTicketsRequest req) {
        Long uID = req.userID.longValue();
        if(!users.existsById(uID)){
            ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid user!"));
        }
        User u = users.findOneById(uID);
        List<Ticket> tickets = repository.findAllByClientID(clients.findOneByUserId(u));
        return tickets.stream().map(TicketTransformer::convertToDto).collect(Collectors.toList());
    }

    /**
     * Mapowanie dodania biletów
     * @param req Ciało zapytania
     *            Integer flightId - ID lotu
     *            Long userId - ID użytkownika
     *            String ticketClass - klasa biletu ("economic" albo "business")     *
     * @return Odpowiedź informująca o rezultacie działania.
     */


    @PostMapping("/add")
    public ResponseEntity<?> newTicket(@Valid @RequestBody AddTicketRequest req){
        //System.out.println("AAAAAAAAAAAAAAAAAAAAA!");
        System.out.println("UID = " +req.userId + " FID = " + req.flightId + " class = " + req.ticketClass + "paid = " + req.paid);
      if(!users.existsById(req.userId)){
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Invalid user!"));
      }

        repository.save(new Ticket(req.ticketClass,
                clients.findOneByUserId(users.findOneById(req.userId)),
                flights.findOneById(req.flightId),
                req.paid, null));

        return ResponseEntity.ok(new MessageResponse("Ticked added successfully!"));
    }

    /**
     *
     * @param req Ciało zapytania
     *            id - ID biletu do usunięcia
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTicket(@Valid @RequestBody DeleteTicketRequest req){
        if(!repository.existsById(req.id)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Ticket does not exist!"));
        }
        repository.deleteById(req.id);
        return ResponseEntity.ok(new MessageResponse("Ticket successfully deleted"));
    }

    /**
     * Mapowanie odprawy (wybranie miejsca, ustawienie flagi zaplaty)
     * @param req Cialo zapytania
     *             ticketID - ID biletu
     *             seat - numer miejsca
     * @return Odpowiedz informujaca o rezultacie dzialania
     */
    @Transactional
    @PostMapping("/checkIn")
    public ResponseEntity<?> checkIn(@Valid @RequestBody CheckInTicketRequest req){
        if(!repository.existsById(req.ticketID)){
            ResponseEntity.badRequest().body(new MessageResponse("Error: Ticket does not exist!"));
        }
        repository.update(true, req.seat, req.ticketID);
        return ResponseEntity.ok(new MessageResponse("Ticket checked in successfully"));
    }
}
