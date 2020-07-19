package com.example.bankmonolith.bootstrap;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.repository.AccountRepository;
import com.example.bankmonolith.repository.CustomerRepository;
import com.example.bankmonolith.service.CustomerService;
import com.example.bankmonolith.service.CustomerServiceImpl;
import com.example.bankmonolith.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class CustomerLoader implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        loadCustomer();
    }

    @Transactional
    public void loadCustomer() throws ChangeSetPersister.NotFoundException {
        Customer customer = customerRepository.save(Customer.builder().
                firstName("Mohammad").
                lastName("Aliabadi").
                nationalNumber("2312321").
                phoneNumber("123213213").
                address("Minab").
                build());
        Account account = accountRepository.save(Account.builder().accountNumber("123213")
                .balance(new BigDecimal(12312321))
                .customer(customer)
                .accountType(2).build());

        accountRepository.deleteById(account.getId());

        customerService.findById(customer.getId()).getAccounts().forEach(System.out::println);

    }
}
