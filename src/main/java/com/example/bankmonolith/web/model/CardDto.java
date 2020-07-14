package com.example.bankmonolith.web.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CardDto extends BaseItem {
    private String cardNumber;
    private int ccv;
    private String password;
    private int validityTime;
    @Builder
    public CardDto(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                   String cardNumber, int ccv, String password, int validityTime) {
        super(id, version, createdDate, lastModifiedDate);
        this.cardNumber = cardNumber;
        this.ccv = ccv;
        this.password = password;
        this.validityTime = validityTime;
    }

}
