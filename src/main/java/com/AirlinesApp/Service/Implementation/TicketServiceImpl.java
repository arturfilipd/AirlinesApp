package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Ticket;
import com.AirlinesApp.Repository.TicketRepository;
import com.AirlinesApp.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    public List<Ticket> getAllTickets(){
        return ticketRepository.getAllTickets();
    }
}
