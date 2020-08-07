package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByCardNumber(String cardNumber);
    @Modifying
    @Query("delete from Card c where c.id=:id")
    void deleteById(@Param("id") UUID id);
}
