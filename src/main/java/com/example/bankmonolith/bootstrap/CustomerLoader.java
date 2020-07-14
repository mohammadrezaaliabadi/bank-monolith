package com.example.bankmonolith.bootstrap;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.repository.CustomerRepository;
import com.example.bankmonolith.service.CustomerService;
import com.example.bankmonolith.service.CustomerServiceImpl;
import com.example.bankmonolith.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerLoader implements CommandLineRunner {
    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        loadCustomer();
    }

    public void loadCustomer(){
        CustomerDto customerDto = customerService.saveCustomer(CustomerDto.builder().
                firstName("Mohammad").
                lastName("Aliabadi").
                nationalNumber("2312321").
                phoneNumber("123213213").
                address("Minab").
                build());
    }
}
