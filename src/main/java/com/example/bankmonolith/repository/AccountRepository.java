package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAccountByAccountNumberStartsWith(String accountNumber, Pageable pageable);
    @Modifying
    @Query("delete from Account a where a.id=:id")
    void deleteById(@Param("id") UUID id);
}
