package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Service.ClientService;
import com.AirlinesApp.Transformer.ClientTransformer;
import com.AirlinesApp.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa kontrolera klientów, mapowanego pod adresem "/api/clients/".
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController{
    private final ClientService clientService;
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getClients(){
        List<Client> clients = clientService.getAllClients();
        return clients.stream().map(ClientTransformer::convertToDto).collect(Collectors.toList());
    }
}
