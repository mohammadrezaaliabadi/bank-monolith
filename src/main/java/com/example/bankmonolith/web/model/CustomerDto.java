package com.example.bankmonolith.web.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem {
    private String firstName;
    private String lastName;
    private String nationalNumber;
    private String phoneNumber;
    private String address;
    private Set<AccountDto> accounts;

    @Builder
    public CustomerDto(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                       String firstName, String lastName, String nationalNumber,
                       String phoneNumber, String address,Set<AccountDto> accounts) {
        super(id, version, createdDate, lastModifiedDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.nationalNumber = nationalNumber;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }
}
