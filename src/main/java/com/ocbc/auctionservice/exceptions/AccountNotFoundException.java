package com.ocbc.auctionservice.exceptions;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
