package com.example.bankmonolith.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Card extends BaseEntity {
    private String cardNumber;
    private int ccv;
    private String password;
    private int validityTime;
    @ManyToOne
    private Account account;
    @Builder
    public Card(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                String cardNumber, int ccv, String password, int validityTime,
                Account account) {
        super(id, version, createdDate, lastModifiedDate);
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.password = password;
        this.validityTime = validityTime;
        this.account = account;
    }

}
