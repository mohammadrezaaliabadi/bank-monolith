package com.example.bankmonolith.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account extends BaseEntity {
    private String accountNumber;
    private int accountType;
    private BigDecimal balance;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<Card> cards = new HashSet<>();
    @OneToMany(mappedBy = "accountFrom",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<Transaction> transactionsFrom = new HashSet<>();
    @OneToMany(mappedBy = "accountTo",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<Transaction> transactionsTo = new HashSet<>();

    @Builder
    public Account(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate
    , String accountNumber, int accountType, BigDecimal balance,
                   Customer customer,Set<Card> cards,
                   Set<Transaction> transactionsFrom, Set<Transaction> transactionsTo) {
        super(id, version, createdDate, lastModifiedDate);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.customer =customer;
        this.cards = cards;
        this.transactionsFrom = transactionsFrom;
        this.transactionsTo = transactionsTo;
    }
}
