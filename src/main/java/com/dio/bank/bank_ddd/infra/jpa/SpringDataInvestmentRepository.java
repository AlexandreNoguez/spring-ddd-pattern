package com.dio.bank.bank_ddd.infra.jpa;

import com.dio.bank.bank_ddd.domain.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataInvestmentRepository extends JpaRepository<Investment, UUID> {
}
