package com.dio.bank.bank_ddd.domain.repo;

import com.dio.bank.bank_ddd.domain.Investment;

public interface InvestmentRepository {
    Investment save(Investment investment);
}
