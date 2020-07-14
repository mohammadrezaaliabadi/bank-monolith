package com.example.bankmonolith.repository;

import com.example.bankmonolith.domain.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findCustomerByNationalNumber(String NationalNumber);
    List<Customer> findCustomersByLastNameStartsWith(String lastName, Pageable pageable);
}
