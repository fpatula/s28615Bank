package com.pjwstk.bank.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @Test
    void shouldRegisterClient() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        when(clientRepository
                .addClient(anyString(),anyString(), anyString(), any(BigDecimal.class)))
                .thenReturn(true);
        clientService.registerClient(clientID,clientName,clientSurname,clientBalance);
    }
    @Test
    void shouldNotRegisterClient() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        when(clientRepository
                .addClient(anyString(),anyString(), anyString(), any(BigDecimal.class)))
                .thenReturn(false);
        clientService.registerClient(clientID,clientName,clientSurname,clientBalance);
    }
}