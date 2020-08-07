package com.example.bankmonolith.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCardInfo {
    private CardDto cardFrom;
    private String cardToCardNumber;
    private String nameFrom;
    private String nameTo;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;
}
