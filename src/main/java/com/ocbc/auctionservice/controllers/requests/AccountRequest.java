package com.ocbc.auctionservice.controllers.requests;

import com.ocbc.auctionservice.entities.Account;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AccountRequest {

    @NotNull(message = "{userRequest.address.notNull}")
    private String accountNumber;

    @NotNull(message = "{userRequest.address.notNull}")
    private Account.AccountType accountType;

    @NotNull(message = "{userRequest.address.notNull}")
    private String currency;

    @NotNull(message = "{userRequest.address.notNull}")
    private boolean userId;
}
