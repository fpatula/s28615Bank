package com.pjwstk.bank.transaction;

import com.pjwstk.bank.client.Client;
import com.pjwstk.bank.client.ClientNotFoundException;
import com.pjwstk.bank.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final ClientService clientService;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(ClientService clientService, TransactionRepository transactionRepository) {
        this.clientService = clientService;
        this.transactionRepository = transactionRepository;
    }


    public Transaction withdrawBalance(String clientID, double value){
        Client client;
        BigDecimal newBalance = null;
        Transaction transaction;
        try{
            client = clientService.getClient(clientID);
            newBalance = client.getBalance().subtract(BigDecimal.valueOf(value));
            if (newBalance.signum() == -1){
                throw new TransactionException("Could not perform transaction, balance too low");
            }
            transaction = new Transaction(clientID, newBalance.doubleValue(), TransactionStatus.ACCEPTED);
            transactionRepository.addTransaction(transaction);
            client.setBalance(newBalance);
            return transaction;
        }
        catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
            double transactionBalance = newBalance!=null?newBalance.doubleValue():-1;
            transaction = new Transaction(clientID, transactionBalance, TransactionStatus.DECLINED);
            transactionRepository.addTransaction(transaction);
        }
        catch (ClientNotFoundException e){
            throw new TransactionException("Could not find client for transaction");
        }
        return transaction;
    }

    public Transaction addBalance(String clientID, double value){
        Client client;
        BigDecimal newBalance;
        Transaction transaction;
        try{
            client = clientService.getClient(clientID);
            newBalance = client.getBalance().add(BigDecimal.valueOf(value));
            transaction = new Transaction(clientID, newBalance.doubleValue(), TransactionStatus.ACCEPTED);
            transactionRepository.addTransaction(transaction);
            client.setBalance(newBalance);
            return transaction;
        }
        catch (ClientNotFoundException e){
            throw new TransactionException("Could not find client for transaction");
        }
    }
}
