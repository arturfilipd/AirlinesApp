package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Ticket;
import com.AirlinesApp.dto.TicketDto;
import org.springframework.beans.BeanUtils;

public class TicketTransformer {
    public static TicketDto convertToDto(Ticket ticket){
        TicketDto ticketDto = new TicketDto();
        BeanUtils.copyProperties(ticket, ticketDto);
        return ticketDto;
    }
}
