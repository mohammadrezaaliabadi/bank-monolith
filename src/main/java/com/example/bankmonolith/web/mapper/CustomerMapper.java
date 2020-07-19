package com.example.bankmonolith.web.mapper;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.web.model.CustomerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = DateMapper.class,unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
