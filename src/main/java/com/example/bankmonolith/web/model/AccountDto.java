package com.example.bankmonolith.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountDto extends BaseItem{
    private String accountNumber;
    private int accountType;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;

    @Builder
    public AccountDto(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String accountNumber, int accountType, BigDecimal balance) {
        super(id, version, createdDate, lastModifiedDate);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

}
