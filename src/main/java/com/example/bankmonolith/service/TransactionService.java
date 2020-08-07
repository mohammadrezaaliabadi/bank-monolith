package com.example.bankmonolith.service;

import com.example.bankmonolith.web.model.AccountCardInfo;
import com.example.bankmonolith.web.model.TransactionDto;
import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    @Transactional
    TransactionDto saveTransaction(UUID accountFromId,UUID accountToId,TransactionDto transactionDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    TransactionDto findById(UUID id) throws ChangeSetPersister.NotFoundException;
    @Transactional
    TransactionDto updateTransactions(UUID id, TransactionDto transactionDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    void deleteTransactions(UUID id) throws ChangeSetPersister.NotFoundException;
    List<TransactionDto> findAll();
    TransactionDto findByTransactionNumber(String transactionNumber);
}
