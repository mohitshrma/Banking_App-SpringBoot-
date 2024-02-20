//DTO(Data Transfer Object) pattern is essential for transferring data between different layers or components of an application.
// DTOs encapsulate and organize data, making communication between layers more efficient.

package com.bankapplication.thejavaacademybank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {

    private String accountName;
    private BigDecimal accountBalance;
    private String accountNumber;
}
