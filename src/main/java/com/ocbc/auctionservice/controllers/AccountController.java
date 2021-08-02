package com.ocbc.auctionservice.controllers;

import com.ocbc.auctionservice.controllers.requests.AccountRequest;
import com.ocbc.auctionservice.entities.Account;
import com.ocbc.auctionservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(accountService.createAccount(accountRequestToAccount(accountRequest)));
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody AccountRequest accountRequest, @PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.updateAccount(accountRequestToAccount(accountRequest), accountNumber));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber){
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok("success");
    }

    private Account accountRequestToAccount(AccountRequest accountRequest) {
        return Account.builder().id(UUID.randomUUID())
                .accountNumber(accountRequest.getAccountNumber())
                .accountType(accountRequest.getAccountType())
                .currency(accountRequest.getCurrency())
                .accountStartDate(new Date())
                .lastActiveDate(new Date())
                .isFreeze(false)
                .userId(accountRequest.isUserId()).build();
    }
}
