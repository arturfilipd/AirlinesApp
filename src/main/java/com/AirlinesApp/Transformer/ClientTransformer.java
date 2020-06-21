package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.dto.ClientDto;
import org.springframework.beans.BeanUtils;

public class ClientTransformer {
    public static ClientDto convertToDto(Client client){
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);
        return clientDto;
    }
    public static Client convertToEntity(ClientDto clientDto){
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
    }
}
