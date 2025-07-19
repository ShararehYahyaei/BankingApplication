package org.example.bankingapplication.service.impl;

import org.example.bankingapplication.dto.AccountDto;
import org.example.bankingapplication.entity.Account;
import org.example.bankingapplication.mapper.AccountMapper;
import org.example.bankingapplication.repository.AccountRepository;
import org.example.bankingapplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public AccountDto getAccountById(Long accountId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return accountMapper.mapToAccountDto(account);

    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(accountId);

    }

    @Override
    public AccountDto depositMoney(Long id, double amount) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto withdrawMoney(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        if (account.getBalance() - amount < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto transferMoney(Long id, double amount, AccountDto accountDto) {
        Account source = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" account does not found"));

        Account target = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new RuntimeException("Target account not found"));

        if (source.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        source.setBalance(source.getBalance() - amount);
        target.setBalance(target.getBalance() + amount);

        accountRepository.save(source);
        accountRepository.save(target);
        AccountDto accountSourceFinal = accountMapper.mapToAccountDto(source);
        return accountSourceFinal;

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDto> allAccountDtos = allAccounts.stream()
                .map(account -> accountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
        return allAccountDtos;
    }


}
