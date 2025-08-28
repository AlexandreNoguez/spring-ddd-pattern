package com.dio.bank.bank_ddd.interfaces.dto;

import com.dio.bank.bank_ddd.domain.AccountType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public class AccountDtos {

    public record OpenAccountRequest(
            @NotNull AccountType type,
            @NotBlank String holderName,
            @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits") String cpf,
            @NotBlank String pixKey
    ) {}

    public record MoneyRequest(
            @NotNull UUID accountId,
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) {}

    public record PixTransferRequest(
            @NotBlank String fromPixKey,
            @NotBlank String toPixKey,
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) {}
}
