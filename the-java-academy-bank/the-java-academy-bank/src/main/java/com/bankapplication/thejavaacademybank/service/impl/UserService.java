package com.bankapplication.thejavaacademybank.service.impl;

import com.bankapplication.thejavaacademybank.dto.*;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);  //BankResponse is return type of method createAccount
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest); //from DTO
    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);

    BankResponse debitAccount(CreditDebitRequest creditDebitRequest);

    BankResponse transfer(TransferRequest transferRequest);

}
