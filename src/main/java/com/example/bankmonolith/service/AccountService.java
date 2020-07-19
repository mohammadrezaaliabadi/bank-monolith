package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.web.model.AccountDto;
import com.example.bankmonolith.web.model.CustomerDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    @Transactional
    AccountDto saveAccount(UUID customerId, AccountDto accountDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    AccountDto findById(UUID id) throws ChangeSetPersister.NotFoundException;
    @Transactional
    AccountDto updateAccount(UUID id,AccountDto accountDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    void deleteAccount(UUID id) throws ChangeSetPersister.NotFoundException;
    List<AccountDto> findAll();
    List<AccountDto> findByAccountNumber(String accountNumber);
}
