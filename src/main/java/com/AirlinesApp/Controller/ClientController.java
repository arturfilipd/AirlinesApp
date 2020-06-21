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

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class ClientController{
    private final ClientService clientService;
    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getClients(){
        List<Client> clients = clientService.getAllClients();
        return clients.stream().map(ClientTransformer::convertToDto).collect(Collectors.toList());
    }
}
