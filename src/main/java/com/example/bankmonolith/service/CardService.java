package com.example.bankmonolith.service;

import com.example.bankmonolith.web.model.CardDto;
import com.example.bankmonolith.web.model.CustomerDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface CardService {
    @Transactional
    CardDto saveCard(UUID accountId,CardDto cardDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    CardDto findById(UUID id) throws ChangeSetPersister.NotFoundException;
    @Transactional
    CardDto updateCard(UUID id,CardDto cardDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    void deleteCard(UUID id) throws ChangeSetPersister.NotFoundException;
    List<CardDto> findAll();
    CardDto findByCardNumber(String cardNumber) throws ChangeSetPersister.NotFoundException;
}
