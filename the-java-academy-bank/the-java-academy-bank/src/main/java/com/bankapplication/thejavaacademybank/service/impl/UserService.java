package com.bankapplication.thejavaacademybank.service.impl;

import com.bankapplication.thejavaacademybank.dto.BankResponse;
import com.bankapplication.thejavaacademybank.dto.EnquiryRequest;
import com.bankapplication.thejavaacademybank.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);  //BankResponse is return type of method createAccount
    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest); //from DTO
    String nameEnquiry(EnquiryRequest enquiryRequest);
}
