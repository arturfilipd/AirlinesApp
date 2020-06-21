package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Repository.ClientRepository;
import com.AirlinesApp.Service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.getAllClients();
    }
}
