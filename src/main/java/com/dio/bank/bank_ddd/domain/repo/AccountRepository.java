package com.dio.bank.bank_ddd.domain.repo;

import com.dio.bank.bank_ddd.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(UUID id);
    Optional<Account> findByPixKey(String pixKey);
}
