package com.dio.bank.bank_ddd.interfaces.dto;

import com.dio.bank.bank_ddd.domain.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public final class AccountDtos {

    public record OpenAccountRequest(
            @Schema(description = "Account type", example = "CHECKING")
            @NotNull AccountType type,

            @Schema(example = "Alex")
            @NotBlank String holderName,

            @Schema(description = "Brazilian CPF (11 digits)", example = "12345678901")
            @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits") String cpf,

            @Schema(description = "PIX key (email/phone/random). Example shows an email-like key.", example = "alex@pix")
            @NotBlank String pixKey
    ) { }

    public record MoneyRequest(
            @Schema(format = "uuid", example = "0b3b3b1b-98b4-4b9b-9a2f-0d8e0d8e0d8e")
            @NotNull UUID accountId,

            @Schema(example = "150.00", minimum = "0.01")
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) { }

    public record PixTransferRequest(
            @Schema(example = "alex@pix")
            @NotBlank String fromPixKey,

            @Schema(example = "b@pix")
            @NotBlank String toPixKey,

            @Schema(example = "75.00", minimum = "0.01")
            @NotNull @DecimalMin("0.01") BigDecimal amount
    ) { }
}
