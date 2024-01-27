package com.pjwstk.bank.client;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientServiceITest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Test
    void shouldRegisterClient() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        clientService.registerClient(clientID,clientName,clientSurname,clientBalance);
        clientRepository.purgeClients();
    }
    @Test
    void shouldNotRegisterSecondClient() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        String secondClientID = "00001";
        String secondClientName = "Maciej";
        String secondClientSurname = "Bialy";
        double secondClientBalance = 365555.3;
        clientService.registerClient(clientID,clientName,clientSurname,clientBalance);
        clientService.registerClient(secondClientID,secondClientName,secondClientSurname,secondClientBalance);
        clientRepository.purgeClients();
    }

    @Test
    void shouldGetClientData(){
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        clientService.registerClient(clientID,clientName,clientSurname,clientBalance);
        Client client = clientService.getClientData(clientID);
        assertEquals(clientID, client.getClientID());
        assertEquals(clientName, client.getName());
        assertEquals(clientSurname, client.getSurname());
        assertEquals(clientBalance, client.getBalance().doubleValue());
    }
}
