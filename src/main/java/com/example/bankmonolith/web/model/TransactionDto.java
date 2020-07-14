package com.example.bankmonolith.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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

    @Builder
    public TransactionDto(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                          String transactionNumber, int transactionType, BigDecimal totalBalance) {
        super(id, version, createdDate, lastModifiedDate);
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.totalBalance = totalBalance;
    }
}
