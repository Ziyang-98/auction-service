package com.ocbc.auctionservice.exceptions;

public class AccountAlreadyExistException extends BusinessException {
    public AccountAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
