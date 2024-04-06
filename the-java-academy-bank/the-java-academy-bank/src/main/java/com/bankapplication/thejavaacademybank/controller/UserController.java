package com.bankapplication.thejavaacademybank.controller;

import com.bankapplication.thejavaacademybank.dto.*;
import com.bankapplication.thejavaacademybank.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Account Management APIs")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(
            summary = "Create New User Account",
            description = "Creating a new user and assigning an account ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest)
    {
        return userService.createAccount(userRequest);
    }


    @Operation(
            summary = "Enquire balance in user account",
            description = "Enquiring about balance in user account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest)
    {
        return userService.balanceEnquiry(enquiryRequest);
    }


    @Operation(
            summary = "Enquire about name in user Account",
            description = "Enquiring about name of user from account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest)
    {
        return userService.nameEnquiry(enquiryRequest);
    }


    @Operation(
            summary = "Crediting balance in user account",
            description = "Crediting balance to the user account"
    )
    @ApiResponse(
            responseCode = "202",
            description = "Http Status 202 ACCEPTED"
    )
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest creditDebitRequest)
    {
        return userService.creditAccount(creditDebitRequest);
    }


    @Operation(
            summary = "Debiting balance from User Account",
            description = "Debiting balance from the user account"
    )
    @ApiResponse(
            responseCode = "202",
            description = "Http Status 202 ACCEPTED"
    )
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest creditDebitRequest)
    {
        return userService.debitAccount(creditDebitRequest);
    }


    @Operation(
            summary = "Transfer amount from source to destination account",
            description = "Transferring balance from one account to another"
    )
    @ApiResponse(
            responseCode = "202",
            description = "Http Status 202 ACCEPTED"
    )
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest transferRequest)
    {
      return userService.transfer(transferRequest);
    }
}
