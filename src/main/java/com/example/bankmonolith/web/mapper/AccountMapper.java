package com.example.bankmonolith.web.mapper;

import com.example.bankmonolith.domain.Account;
import com.example.bankmonolith.web.model.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = DateMapper.class,unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AccountMapper {
    AccountDto accountToAccountDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);

}
