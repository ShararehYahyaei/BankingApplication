package org.example.bankingapplication.dto;

import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter

public class AccountDto {
    private Long id;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
    public AccountDto(Long id, String accountHolderName, double balance) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
    public Long getId() {
        return id;
    }

    public AccountDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
