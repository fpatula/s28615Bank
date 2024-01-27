package com.pjwstk.bank.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Client {
    private final String clientID;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    @EqualsAndHashCode.Exclude
    private BigDecimal balance;
}
