package com.dio.bank.bank_ddd.infra.adapter;

import com.dio.bank.bank_ddd.domain.Account;
import com.dio.bank.bank_ddd.domain.repo.AccountRepository;
import com.dio.bank.bank_ddd.infra.jpa.SpringDataAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {

    private final SpringDataAccountRepository jpaRepo;

    @Override
    public Account save(Account account) {
        return jpaRepo.save(account);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepo.findById(id);
    }

    @Override
    public Optional<Account> findByPixKey(String pixKey) {
        return jpaRepo.findByPixKey(pixKey);
    }
}
