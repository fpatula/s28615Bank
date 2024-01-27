package com.pjwstk.bank.transaction;

import com.pjwstk.bank.client.ClientRepository;
import com.pjwstk.bank.client.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TransactionServiceITest {

    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionService transactionService;

    @Test
    void withdrawBalanceSuccessfully() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        clientService.registerClient(clientID, clientName, clientSurname, clientBalance);
        Transaction transaction = transactionService.withdrawBalance(clientID, 32000);
        clientRepository.purgeClients();
        assertEquals(2555.3, transaction.getBalance());
    }
    @Test
    void addBalanceSuccessfully() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        clientService.registerClient(clientID,clientName,clientSurname,clientBalance);
        Transaction transaction = transactionService.addBalance(clientID, 4000);
        clientRepository.purgeClients();
        assertEquals(38555.3, transaction.getBalance());
    }

    @Test
    void withdrawBalanceFailure() {
        String clientID = "00001";
        String clientName = "Andrzej";
        String clientSurname = "Bialy";
        double clientBalance = 34555.3;
        clientService.registerClient(clientID, clientName, clientSurname, clientBalance);
        Transaction transaction = transactionService.withdrawBalance(clientID, 38000);
        clientRepository.purgeClients();
        assertEquals(TransactionStatus.DECLINED, transaction.getStatus());
    }
}