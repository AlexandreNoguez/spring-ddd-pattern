package com.dio.bank.bank_ddd.application;

import com.dio.bank.bank_ddd.domain.*;
import com.dio.bank.bank_ddd.domain.repo.AccountRepository;
import com.dio.bank.bank_ddd.domain.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;
    private final TransactionRepository txRepo;

    @Transactional
    public Account open(AccountType type, String holderName, String cpf, String pixKey) {
        Account acc = Account.open(type, holderName, cpf, pixKey);
        return accountRepo.save(acc);
    }

    @Transactional
    public Account deposit(UUID accountId, BigDecimal amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        acc.deposit(amount);
        accountRepo.save(acc);
        txRepo.save(Transaction.of(acc.getId(), TransactionType.DEPOSIT, amount, "Deposit"));
        return acc;
    }

    @Transactional
    public Account withdraw(UUID accountId, BigDecimal amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        acc.withdraw(amount);
        accountRepo.save(acc);
        txRepo.save(Transaction.of(acc.getId(), TransactionType.WITHDRAW, amount, "Withdraw"));
        return acc;
    }

    @Transactional
    public void pixTransfer(String fromPixKey, String toPixKey, BigDecimal amount) {
        if (fromPixKey.equals(toPixKey)) throw new IllegalArgumentException("Transfer to same key is not allowed");
        Account from = accountRepo.findByPixKey(fromPixKey)
                .orElseThrow(() -> new IllegalArgumentException("From account not found"));
        Account to = accountRepo.findByPixKey(toPixKey)
                .orElseThrow(() -> new IllegalArgumentException("To account not found"));

        from.withdraw(amount);
        to.deposit(amount);
        accountRepo.save(from);
        accountRepo.save(to);

        txRepo.save(Transaction.of(from.getId(), TransactionType.PIX_SENT, amount, "PIX sent to " + toPixKey));
        txRepo.save(Transaction.of(to.getId(), TransactionType.PIX_RECEIVED, amount, "PIX received from " + fromPixKey));
    }

    @Transactional(readOnly = true)
    public List<Transaction> listTransactions(UUID accountId) {
        return txRepo.findByAccountIdOrderByCreatedAtDesc(accountId);
    }
}
