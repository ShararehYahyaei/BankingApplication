package org.example.bankingapplication.service;

import org.example.bankingapplication.dto.AccountDto;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long accountId);
    void deleteAccount(Long accountId);
    AccountDto depositMoney(Long id, double amount);
    AccountDto withdrawMoney(Long id, double amount);
    AccountDto transferMoney(Long id, double amount, AccountDto accountDto);
    List<AccountDto> getAllAccounts();
}
