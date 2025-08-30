package com.dio.bank.bank_ddd.interfaces.dto;

import com.dio.bank.bank_ddd.domain.InvestmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public final class InvestmentDtos {
    public record CreateInvestmentRequest(
            @Schema(format = "uuid", example = "2a9f8a7c-1b2c-4d5e-9f01-234567890abc")
            @NotNull UUID accountId,

            @Schema(description = "Investment type", example = "CDI")
            @NotNull InvestmentType type,

            @Schema(example = "200.00", minimum = "0.01")
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) { }
}
