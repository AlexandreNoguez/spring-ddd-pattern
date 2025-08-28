package com.dio.bank.bank_ddd.infra.adapter;

import com.dio.bank.bank_ddd.domain.Investment;
import com.dio.bank.bank_ddd.domain.repo.InvestmentRepository;
import com.dio.bank.bank_ddd.infra.jpa.SpringDataInvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvestmentRepositoryAdapter implements InvestmentRepository {

    private final SpringDataInvestmentRepository jpaRepo;

    @Override
    public Investment save(Investment investment) {
        return jpaRepo.save(investment);
    }
}
