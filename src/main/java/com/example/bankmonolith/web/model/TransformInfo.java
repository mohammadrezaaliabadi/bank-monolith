package com.example.bankmonolith.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransformInfo {
    private String transactionNumber;
    private String fromName;
    private String toName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;
}
