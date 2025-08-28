package com.dio.bank.bank_ddd.domain.repo;

import com.dio.bank.bank_ddd.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction tx);
    List<Transaction> findByAccountIdOrderByCreatedAtDesc(UUID accountId);
}
