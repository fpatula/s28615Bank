package com.pjwstk.bank.client;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
