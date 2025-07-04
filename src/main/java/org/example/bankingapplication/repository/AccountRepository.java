package org.example.bankingapplication.repository;

import org.example.bankingapplication.dto.AccountDto;
import org.example.bankingapplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    AccountDto getAccountById(Long id);

    Long id(Long id);
}
