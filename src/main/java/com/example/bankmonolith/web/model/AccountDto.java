package com.example.bankmonolith.web.model;

import com.example.bankmonolith.domain.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDto extends BaseItem{
    private String accountNumber;
    private int accountType;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;
    private CustomerDto customerDto;
    private Set<CardDto> cards;
    private Set<TransactionDto> transactionsFrom;
    private Set<TransactionDto> transactionsTo;

    @Builder
    public AccountDto(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                      String accountNumber, int accountType, BigDecimal balance,
                      CustomerDto customerDto, Set<CardDto> cards,
                      Set<TransactionDto> transactionsFrom,Set<TransactionDto> transactionsTo) {
        super(id, version, createdDate, lastModifiedDate);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.customerDto = customerDto;
        this.cards = cards;
        this.transactionsFrom = transactionsFrom;
        this.transactionsTo = transactionsTo;
    }

}
