package org.example.bankingapplication.service;

import org.example.bankingapplication.dto.AccountDto;

import javax.security.auth.login.AccountNotFoundException;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto updateAccount(AccountDto accountDto);
    AccountDto getAccountById(Long accountId);
    AccountDto deleteAccount(Long accountId);
    AccountDto depositMoney(Long id, double amount);
}
