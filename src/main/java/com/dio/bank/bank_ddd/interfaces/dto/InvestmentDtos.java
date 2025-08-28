package com.dio.bank.bank_ddd.interfaces.dto;

import com.dio.bank.bank_ddd.domain.InvestmentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class InvestmentDtos {
    public record CreateInvestmentRequest(
            @NotNull UUID accountId,
            @NotNull InvestmentType type,
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) {}
}
