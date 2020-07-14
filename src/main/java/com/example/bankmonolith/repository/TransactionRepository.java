package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Transaction findByTransactionNumber(String transactionNumber);
}
