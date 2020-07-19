package com.example.bankmonolith.service;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.repository.CustomerRepository;
import com.example.bankmonolith.web.mapper.CustomerMapper;
import com.example.bankmonolith.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customerDto)));
    }

    @Override
    public CustomerDto findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return customerMapper.customerToCustomerDto(customerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public CustomerDto updateCustomer(UUID uuid,CustomerDto customerDto) throws ChangeSetPersister.NotFoundException {
        Customer customer = customerRepository.findById(uuid).orElseThrow(ChangeSetPersister.NotFoundException::new);
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customer.setNationalNumber(customerDto.getNationalNumber());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(customer);
    }

    @Override
    public void deleteCustomer(UUID id) throws ChangeSetPersister.NotFoundException {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll(PageRequest.of(0,10, Sort.Direction.ASC,"lastName")).stream().map(customer -> customerMapper.customerToCustomerDto(customer)).collect(Collectors.toList());
    }

    public CustomerDto getByNationalNumber(String nationalNumber){
        return customerMapper.customerToCustomerDto(customerRepository.findCustomerByNationalNumber(nationalNumber));
    }

    @Override
    public List<CustomerDto> findByLastName(String lastName) {
        return customerRepository.findCustomersByLastNameStartsWith(lastName,PageRequest.of(0,10, Sort.Direction.ASC,"lastName")).stream().map(customer -> customerMapper.customerToCustomerDto(customer)).collect(Collectors.toList());
    }

}
