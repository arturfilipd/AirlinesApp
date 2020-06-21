package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Ticket;
import com.AirlinesApp.Service.TicketService;
import com.AirlinesApp.Transformer.TicketTransformer;
import com.AirlinesApp.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class TicketController {
    private final TicketService ticketService;
    @GetMapping("/tickets")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();
        return tickets.stream().map(TicketTransformer::convertToDto).collect(Collectors.toList());
    }
}
