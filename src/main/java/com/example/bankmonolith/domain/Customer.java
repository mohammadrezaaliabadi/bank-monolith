package com.example.bankmonolith.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String nationalNumber;
    private String phoneNumber;
    private String address;
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @Builder
    public Customer(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                    String firstName, String lastName, String nationalNumber, String phoneNumber,
                    String address, Set<Account> accounts) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nationalNumber = nationalNumber;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }
}
