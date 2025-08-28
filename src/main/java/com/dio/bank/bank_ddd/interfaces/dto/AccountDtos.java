package com.dio.bank.bank_ddd.interfaces.dto;

import com.dio.bank.bank_ddd.domain.AccountType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public class AccountDtos {

    public record OpenAccountRequest(
            @Schema(example = "CHECKING") @NotNull AccountType type,
            @Schema(example = "Alex") @NotBlank String holderName,
            @Schema(example = "12345678901") @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits") String cpf,
            @Schema(example = "alex@pix") @NotBlank String pixKey
            @NotNull AccountType type,
            @NotBlank String holderName,
            @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits") String cpf,
            @NotBlank String pixKey
    ) {}

    public record MoneyRequest(
            @Schema(example = "0b3b3b1b-98b4-4b9b-9a2f-0d8e0d8e0d8e") @NotNull UUID accountId,
            @Schema(example = "150.00", minimum = "0.01") @NotNull @DecimalMin("0.01") BigDecimal amount
            @NotNull UUID accountId,
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) {}

    public record PixTransferRequest(
            @Schema(example = "alex@pix") @NotBlank String fromPixKey,
            @Schema(example = "b@pix") @NotBlank String toPixKey,
            @Schema(example = "75.00", minimum = "0.01") @NotNull @DecimalMin("0.01") BigDecimal amount
            @NotBlank String fromPixKey,
            @NotBlank String toPixKey,
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) {}
}
