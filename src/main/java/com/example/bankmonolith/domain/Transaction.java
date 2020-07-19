package com.example.bankmonolith.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Transaction extends BaseEntity {
    private String transactionNumber;
    private int transactionType;
    private BigDecimal totalBalance;
    @ManyToOne
    private Account accountFrom;
    @ManyToOne
    private Account accountTo;

    @Builder
    public Transaction(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                       String transactionNumber, int transactionType, BigDecimal totalBalance,
                       Account accountFrom, Account accountTo) {
        super(id, version, createdDate, lastModifiedDate);
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.totalBalance = totalBalance;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }
}
