package com.bankapplication.thejavaacademybank.service.impl;

import com.bankapplication.thejavaacademybank.dto.TransactionDto;
import com.bankapplication.thejavaacademybank.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);

}
