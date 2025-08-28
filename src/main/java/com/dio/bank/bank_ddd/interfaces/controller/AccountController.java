package com.dio.bank.bank_ddd.interfaces.controller;

import com.dio.bank.bank_ddd.application.AccountService;
import com.dio.bank.bank_ddd.domain.Account;
import com.dio.bank.bank_ddd.domain.Transaction;
import com.dio.bank.bank_ddd.interfaces.dto.AccountDtos.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Account")
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @Operation(summary = "Open a new bank account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Account created"),
        @ApiResponse(responseCode = "400", description = "Validation error",
            content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    @PostMapping
    public ResponseEntity<Account> open(@Valid @RequestBody OpenAccountRequest req) {
        Account created = service.open(req.type(), req.holderName(), req.cpf(), req.pixKey());
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Deposit money into an account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deposit successful"),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@Valid @RequestBody MoneyRequest req) {
        return ResponseEntity.ok(service.deposit(req.accountId(), req.amount()));
    }

    @Operation(summary = "Withdraw money from an account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Withdraw successful"),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@Valid @RequestBody MoneyRequest req) {
        return ResponseEntity.ok(service.withdraw(req.accountId(), req.amount()));
    }

    @Operation(summary = "Transfer via PIX using PIX keys")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "PIX successful"),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    @PostMapping("/pix")
    public ResponseEntity<Void> pix(@Valid @RequestBody PixTransferRequest req) {
        service.pixTransfer(req.fromPixKey(), req.toPixKey(), req.amount());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "List transactions for an account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transactions returned"),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> transactions(@PathVariable UUID accountId) {
        return ResponseEntity.ok(service.listTransactions(accountId));
    }
}
