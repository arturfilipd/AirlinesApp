package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.AuthProvider;
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
import com.AirlinesApp.Security.TokenProvider;
import com.AirlinesApp.Security.jwt.JwtUtils;
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

    @Autowired JwtUtils jwtUtils;
    @Autowired TokenProvider oa2Utils;

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
     *            String ticketClass - klasa biletu ("economic" albo "business")
     * @param Authorization Token JWT uwierzytelniający użytkownika przesyłany w nagłówku
     * @return Odpowiedź informująca o rezultacie działania.
     */


    @PostMapping("/add")
    public ResponseEntity<?> newTicket(@Valid @RequestBody AddTicketRequest req, @RequestHeader String Authorization){
        if (!flights.existsById(req.flightId)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No such flight!"));
        }
        if(!users.existsById(req.userId)){
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Invalid user!"));
      }
        String tokenName = "";
        if(users.findOneById(req.userId).getProvider() == AuthProvider.local){
            tokenName= jwtUtils.getUserNameFromJwtToken(Authorization.substring(7));
        }
        if(users.findOneById(req.userId).getProvider() == AuthProvider.facebook){
            tokenName= users.findOneById(oa2Utils.getUserIdFromToken(Authorization.substring(7))).getUsername();
        }

        if(!tokenName.equals(users.findOneById(req.userId).getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid user!"));
        }
        if(!flights.existsById(req.flightId)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Flight does not exist!"));
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
     * @param Authorization Token JWT uwierzytelniający użytkownika przesyłany w nagłówku
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTicket(@Valid @RequestBody DeleteTicketRequest req, @RequestHeader String Authorization){
        if(!repository.existsById(req.id)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Ticket does not exist!"));
        }
        String tokenName = "";
        AuthProvider prov =repository.findOneById(req.id).getClientID().getUserId().getProvider();
        if(prov == AuthProvider.local){
            tokenName= jwtUtils.getUserNameFromJwtToken(Authorization.substring(7));
        }
        if(prov == AuthProvider.facebook){
            tokenName= users.findOneById(oa2Utils.getUserIdFromToken(Authorization.substring(7))).getUsername();
        }
        String username = clients.findOneById(repository.findOneById(req.id).getClientID().getId()).getUserId().getUsername();
        if(!tokenName.equals(username)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Unauthorized!"));
        }
        repository.deleteById(req.id);
        return ResponseEntity.ok(new MessageResponse("Ticket successfully deleted"));
    }

    /**
     * Mapowanie odprawy (wybranie miejsca, ustawienie flagi zaplaty)
     * @param req Cialo zapytania
     *             ticketID - ID biletu
     *             seat - numer miejsca
     * @param Authorization Token JWT uwierzytelniający użytkownika przesyłany w nagłówku
     * @return Odpowiedz informujaca o rezultacie dzialania
     */
    @Transactional
    @PostMapping("/checkIn")
    public ResponseEntity<?> checkIn(@Valid @RequestBody CheckInTicketRequest req, @RequestHeader String Authorization){
        if(!repository.existsById(req.ticketID)){
            ResponseEntity.badRequest().body(new MessageResponse("Error: Ticket does not exist!"));
        }
        AuthProvider prov = repository.findOneById(req.ticketID).getClientID().getUserId().getProvider();
        String tokenName = "";
        if(prov == AuthProvider.local){
            tokenName= jwtUtils.getUserNameFromJwtToken(Authorization.substring(7));
        }
        if(prov == AuthProvider.facebook){
            tokenName= users.findOneById(oa2Utils.getUserIdFromToken(Authorization)).getUsername();
        }
        String username = repository.findOneById(req.ticketID).getClientID().getUserId().getUsername();
        if(!tokenName.equals(username)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Unauthorized!"));
        }
        repository.update(true, req.seat, req.ticketID);
        return ResponseEntity.ok(new MessageResponse("Ticket checked in successfully"));
    }
}
