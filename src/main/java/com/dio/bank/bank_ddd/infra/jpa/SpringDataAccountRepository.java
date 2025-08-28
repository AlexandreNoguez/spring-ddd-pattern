package com.dio.bank.bank_ddd.infra.jpa;

import com.dio.bank.bank_ddd.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataAccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByPixKey(String pixKey);
}

