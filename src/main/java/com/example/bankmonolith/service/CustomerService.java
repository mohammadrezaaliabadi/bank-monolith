package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.web.model.CustomerDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    @Transactional
    CustomerDto saveCustomer(CustomerDto customerDto);
    @Transactional
    CustomerDto findById(UUID id) throws ChangeSetPersister.NotFoundException;
    @Transactional
    CustomerDto updateCustomer(UUID id,CustomerDto customerDto) throws ChangeSetPersister.NotFoundException;
    @Transactional
    void deleteCustomer(UUID id) throws ChangeSetPersister.NotFoundException;
    List<CustomerDto> findAll();
    List<CustomerDto> findByLastName(String lastName);
}
