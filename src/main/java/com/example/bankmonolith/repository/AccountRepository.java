package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findAccountByAccountNumber(String accountNumber);
}
