package com.example.bankmonolith.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionDto extends BaseItem {
    private String transactionNumber;
    private int transactionType;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal totalBalance;
    private AccountDto accountFrom;
    private AccountDto accountTo;

    @Builder
    public TransactionDto(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                          String transactionNumber, int transactionType, BigDecimal totalBalance,
                          AccountDto accountFrom, AccountDto accountTo) {
        super(id, version, createdDate, lastModifiedDate);
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.totalBalance = totalBalance;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }
}
