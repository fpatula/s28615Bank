package com.pjwstk.bank.transaction;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TransactionRepository {
    private final List<Transaction> transactionList = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    public Collection<Transaction> getAllTransactions(){
        return transactionList;
    }
}
