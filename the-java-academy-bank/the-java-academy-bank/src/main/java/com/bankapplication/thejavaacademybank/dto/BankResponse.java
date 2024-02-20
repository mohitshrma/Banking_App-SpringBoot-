package com.bankapplication.thejavaacademybank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//1. @Data annotation is another main Lombok annotation. Basically, it is an all-in-one annotation that includes
// @ToString, @Getter, @Setter, @EqualsAndHashCode, and @RequiredArgsConstructor



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponse {

    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;

}
