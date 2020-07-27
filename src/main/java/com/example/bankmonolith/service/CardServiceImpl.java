package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.repository.AccountRepository;
import com.example.bankmonolith.repository.CardRepository;
import com.example.bankmonolith.web.mapper.CardMapper;
import com.example.bankmonolith.web.model.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository repository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardMapper mapper;


    @Override
    public CardDto saveCard(UUID accountId, CardDto cardDto) throws ChangeSetPersister.NotFoundException {
        Account account = accountRepository.findById(accountId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Card card = mapper.cardDtoToCard(cardDto);
        card.setAccount(account);
        return mapper.cardToCardDto(repository.save(card));
    }

    @Override
    public CardDto findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return mapper.cardToCardDto(repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public CardDto updateCard(UUID id, CardDto cardDto) throws ChangeSetPersister.NotFoundException {
        Card card = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        card.setCardNumber(cardDto.getCardNumber());
        card.setCcv(cardDto.getCcv());
        card.setPassword(cardDto.getPassword());
        card.setValidityTime(cardDto.getValidityTime());
        return mapper.cardToCardDto(repository.save(card));
    }

    @Override
    public void deleteCard(UUID id) throws ChangeSetPersister.NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public List<CardDto> findAll() {
        return repository.findAll().stream().parallel().map(mapper::cardToCardDto).collect(Collectors.toList());
    }

    @Override
    public CardDto findByCardNumber(String cardNumber) {
        return mapper.cardToCardDto(repository.findByCardNumber(cardNumber));
    }
}
