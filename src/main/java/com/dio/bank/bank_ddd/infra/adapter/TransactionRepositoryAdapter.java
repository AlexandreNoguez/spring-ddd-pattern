package com.dio.bank.bank_ddd.infra.adapter;

import com.dio.bank.bank_ddd.domain.Transaction;
import com.dio.bank.bank_ddd.domain.repo.TransactionRepository;
import com.dio.bank.bank_ddd.infra.jpa.SpringDataTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final SpringDataTransactionRepository jpaRepo;

    @Override
    public Transaction save(Transaction tx) {
        return jpaRepo.save(tx);
    }

    @Override
    public List<Transaction> findByAccountIdOrderByCreatedAtDesc(UUID accountId) {
        return jpaRepo.findByAccountIdOrderByCreatedAtDesc(accountId);
    }
}
