package com.dio.bank.bank_ddd.application;

import com.dio.bank.bank_ddd.domain.*;
import com.dio.bank.bank_ddd.domain.repo.AccountRepository;
import com.dio.bank.bank_ddd.domain.repo.InvestmentRepository;
import com.dio.bank.bank_ddd.domain.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final AccountRepository accountRepo;
    private final InvestmentRepository investmentRepo;
    private final TransactionRepository txRepo;

    @Transactional
    public Investment create(UUID accountId, InvestmentType type, BigDecimal amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        acc.withdraw(amount); // lock money into investment
        accountRepo.save(acc);

        Investment inv = Investment.create(acc.getId(), type, amount);
        inv = investmentRepo.save(inv);

        txRepo.save(Transaction.of(acc.getId(), TransactionType.INVESTMENT_CREATED, amount,
                "Investment created: " + type));

        return inv;
    }
}
