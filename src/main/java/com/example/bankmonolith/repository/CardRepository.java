package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Card findByCardNumber(String cardNumber);
}
