package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.web.model.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ComponentScan(basePackages = "com.example.bankmonolith.web.mapper")
class CustomerServiceImplTest {
    @Autowired
    CustomerService customerService;

    private CustomerDto customerDto;

    @BeforeEach
    void createCustomer(){
        customerDto = customerService.saveCustomer(geCustomerValidated());
    }


    @Test
    public void findById() throws ChangeSetPersister.NotFoundException {
        CustomerDto customerDto1 = customerService.findById(customerDto.getId());
        assertEquals(customerDto1.getFirstName(),customerDto.getFirstName());
    }
    @Test
    public void updateTest() throws ChangeSetPersister.NotFoundException {
        String firstName = "mina";
        customerDto.setFirstName(firstName);
        CustomerDto customerDto2 = customerService.updateCustomer(customerDto.getId(), customerDto);
        assertEquals(customerDto2.getFirstName(),firstName);
    }

    @Test
    public void testDelete() throws ChangeSetPersister.NotFoundException {
        customerService.deleteCustomer(customerDto.getId());
        Assertions.assertThrows(ChangeSetPersister.NotFoundException.class,()->{
            customerService.findById(customerDto.getId());
        });
    }

    CustomerDto geCustomerValidated(){
        return CustomerDto.builder().
                firstName("Mohammad").
                lastName("Aliabadi").
                nationalNumber("234234214").
                address("Minab").build();

    }

}