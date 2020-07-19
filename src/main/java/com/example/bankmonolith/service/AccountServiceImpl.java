package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.repository.AccountRepository;
import com.example.bankmonolith.repository.CustomerRepository;
import com.example.bankmonolith.web.mapper.AccountMapper;
import com.example.bankmonolith.web.model.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public AccountDto saveAccount(UUID customerId, AccountDto accountDto) throws ChangeSetPersister.NotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Account account = accountMapper.accountDtoToAccount(accountDto);
        account.setCustomer(customer);
        return accountMapper.accountToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return accountMapper.accountToAccountDto(accountRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public AccountDto updateAccount(UUID id, AccountDto accountDto) throws ChangeSetPersister.NotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getBalance());
        return accountMapper.accountToAccountDto(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(UUID id) throws ChangeSetPersister.NotFoundException {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll(PageRequest.of(0,10, Sort.Direction.ASC,"accountNumber")).stream().map(account -> accountMapper.accountToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> findByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumberStartsWith(accountNumber,PageRequest.of(0,10, Sort.Direction.ASC,"accountNumber")).stream().map(account -> accountMapper.accountToAccountDto(account)).collect(Collectors.toList());
    }
}
