package com.pjwstk.bank.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void registerClient(String clientID, String name, String surname, double balance){
        if(clientRepository.addClient(clientID, name, surname, BigDecimal.valueOf(balance))){
            System.out.println("Client registered successfully");
        }
        else{
            System.out.println("Could not register client");
        }
    }

    public Client getClient(String clientID){
        Optional<Client> client =  clientRepository.findClient(clientID);
        if (client.isEmpty()){
            System.out.println("Could not find client with given id");
            throw new ClientNotFoundException("Could not find client with id: " + clientID);
        }
        else{
            return client.get();
        }
    }
    public Client getClientData(String clientID){
        Client foundClient = getClient(clientID);
        System.out.println(foundClient);
        return foundClient;
    }
}
