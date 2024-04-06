package com.bankapplication.thejavaacademybank.respository;

import com.bankapplication.thejavaacademybank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
