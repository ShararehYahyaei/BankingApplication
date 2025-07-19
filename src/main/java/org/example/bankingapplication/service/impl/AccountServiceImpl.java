package org.example.bankingapplication.service.impl;

import org.example.bankingapplication.dto.AccountDto;
import org.example.bankingapplication.entity.Account;
import org.example.bankingapplication.mapper.AccountMapper;
import org.example.bankingapplication.repository.AccountRepository;
import org.example.bankingapplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;


    AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepository.save(account);
        return accountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        Account save = accountRepository.save(accountMapper.mapToAccount(accountDto));
        return accountMapper.mapToAccountDto(save);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return accountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto deleteAccount(Long accountId) {
        AccountDto accountById = accountRepository.getAccountById(accountId);
        accountRepository.deleteById(accountId);
        return accountById;
    }

    @Override
    public AccountDto depositMoney(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto withdrawMoney(Long id, double amount) {
        return null;
    }

    @Override
    public AccountDto transferMoney(Long id, double amount, AccountDto accountDto) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : allAccounts) {
            accountDtos.add(accountMapper.mapToAccountDto(account));
        }
        return accountDtos;
    }

}
