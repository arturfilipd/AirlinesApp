package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Client;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TicketDto {
    @Getter @Setter private Integer id;
    @Getter @Setter private String className;
    @Getter @Setter private Long price;
    @Getter @Setter private Client clientID;
}
