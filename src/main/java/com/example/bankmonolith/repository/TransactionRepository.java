package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Transaction findByTransactionNumber(String transactionNumber);
    @Modifying
    @Query("delete from Transaction t where t.id=:id")
    void deleteById(@Param("id") UUID id);
}
