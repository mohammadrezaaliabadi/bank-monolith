package com.example.bankmonolith.bootstrap;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Card;
import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.domain.Transaction;
import com.example.bankmonolith.repository.AccountRepository;
import com.example.bankmonolith.repository.CardRepository;
import com.example.bankmonolith.repository.CustomerRepository;
import com.example.bankmonolith.repository.TransactionRepository;
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
public class AppLoader implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;

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

        Card card = cardRepository.save(Card.builder().cardNumber("21321213213213").validityTime(1112).password("1234122").ccv(321).account(account).build());

        Customer customer2 = customerRepository.save(Customer.builder().
                firstName("ALi").
                lastName("Ahmadi").
                nationalNumber("423423").
                phoneNumber("5435435").
                address("Tehran").
                build());
        Account account2 = accountRepository.save(Account.builder().accountNumber("321241")
                .balance(new BigDecimal(41234234))
                .customer(customer2)
                .accountType(1).build());

        Card card2 = cardRepository.save(Card.builder().cardNumber("423423424234234").validityTime(134234234).password("1232131").ccv(231).account(account2).build());


        Transaction transaction = Transaction.builder().transactionNumber("123213").totalBalance(new BigDecimal(1233)).accountFrom(account).build();
        Transaction save = transactionRepository.save(transaction);

    }
}
