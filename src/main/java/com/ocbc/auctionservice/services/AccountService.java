package com.ocbc.auctionservice.services;

import com.ocbc.auctionservice.entities.Account;
import com.ocbc.auctionservice.exceptions.AccountAlreadyExistException;
import com.ocbc.auctionservice.exceptions.AccountNotFoundException;
import com.ocbc.auctionservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccount(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(String.format("Account id %s does not exist", accountNumber)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account createAccount(Account account) {
        if (accountRepository.findAccountByAccountNumber(account.getAccountNumber()).isPresent()) {
            throw new AccountAlreadyExistException(String.format("Account id %s already exists", account.getAccountType()));
        }
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account updateAccount(Account account, String accountNumber) {
        Account existingAccount = getAccount(accountNumber);
        existingAccount.setAccountNumber(account.getAccountNumber());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setCurrency(account.getCurrency());
        existingAccount.setUserId(account.isUserId());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(String accountNumber) {
        Account existingAccount = getAccount(accountNumber);
        accountRepository.delete(existingAccount);
    }
}
