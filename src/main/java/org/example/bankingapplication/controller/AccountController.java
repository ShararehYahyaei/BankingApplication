package org.example.bankingapplication.controller;

import org.example.bankingapplication.dto.AccountDto;
import org.example.bankingapplication.entity.Account;
import org.example.bankingapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    //todo add new account with rest APi

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }


    //todo get Account  with Rest Api

   // @GetMapping("/getAccount/{id}")


    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById( @PathVariable Long accountId) {
        AccountDto accountById = accountService.getAccountById(accountId);
        return new ResponseEntity<>(accountById, HttpStatus.OK);
    }


}
