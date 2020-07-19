package com.example.bankmonolith.web.mapper;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.domain.Transaction;
import com.example.bankmonolith.web.model.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = DateMapper.class,unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface TransactionMapper {
    TransactionDto transactionToTransactionDto(Transaction transaction);
    Transaction transactionDtoToTransaction(TransactionDto transactionDto);
}
