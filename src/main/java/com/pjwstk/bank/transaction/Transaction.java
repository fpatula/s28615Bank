package com.pjwstk.bank.transaction;

import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Data
public class Transaction {
    private final String clientId;
    private final Instant transactionTimestamp = Instant.now();
    private final double balance;
    @NonNull
    @Setter
    private TransactionStatus status;
}
