package com.pjwstk.bank.client;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ClientRepository {
    private final Set<Client> clients = new HashSet<>();

    public boolean addClient(String clientID, String name, String surname, BigDecimal balance) {
        if (findClient(clientID).isEmpty()) {
            Client client = new Client(clientID, name, surname, balance);
            return clients.add(client);
        }
        return false;
    }

    public Optional<Client> findClient(String clientID) {
        return clients.stream().filter(client -> client.getClientID().equals(clientID)).findFirst();
    }

    public Collection<Client> getAllClients() {
        return clients;
    }
}
