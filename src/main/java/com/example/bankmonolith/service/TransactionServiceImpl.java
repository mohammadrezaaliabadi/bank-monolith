package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Transaction;
import com.example.bankmonolith.repository.AccountRepository;
import com.example.bankmonolith.repository.TransactionRepository;
import com.example.bankmonolith.web.mapper.TransactionMapper;
import com.example.bankmonolith.web.model.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private TransactionMapper mapper;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransactionDto saveTransaction(UUID accountFromId, UUID accountToId, TransactionDto transactionDto) throws ChangeSetPersister.NotFoundException {

        Account accountFrom = accountRepository.findById(accountFromId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Account accountTo = accountRepository.findById(accountToId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Transaction transaction = mapper.transactionDtoToTransaction(transactionDto);
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
        return mapper.transactionToTransactionDto(repository.save(transaction));
    }

    @Override
    public TransactionDto findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return mapper.transactionToTransactionDto(repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public TransactionDto updateTransactions(UUID id, TransactionDto transactionDto) throws ChangeSetPersister.NotFoundException {
        Transaction transaction = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTransactionNumber(transactionDto.getTransactionNumber());
        transaction.setTotalBalance(transactionDto.getTotalBalance());
        return mapper.transactionToTransactionDto(repository.save(transaction));
    }

    @Override
    public void deleteTransactions(UUID id) throws ChangeSetPersister.NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll().stream().map(mapper::transactionToTransactionDto).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findByTransactionNumber(String transactionNumber) {
        return mapper.transactionToTransactionDto(repository.findByTransactionNumber(transactionNumber));
    }
}
