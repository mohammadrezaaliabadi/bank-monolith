package com.example.bankmonolith.web.mapper;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Transaction;
import com.example.bankmonolith.web.model.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = DateMapper.class,unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "accountTo" ,ignore = true)
    @Mapping(target = "accountFrom" ,ignore = true)
    TransactionDto transactionToTransactionDto(Transaction transaction);
    Transaction transactionDtoToTransaction(TransactionDto transactionDto);
}
