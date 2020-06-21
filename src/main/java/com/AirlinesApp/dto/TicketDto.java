package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Client;
import lombok.Data;

@Data
public class TicketDto {
    private Integer id;
    private String className;
    private Long price;
    private Client clientID;
}
