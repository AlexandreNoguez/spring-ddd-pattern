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

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public ResponseEntity<Account> open(@Valid @RequestBody OpenAccountRequest req) {
        Account created = service.open(req.type(), req.holderName(), req.cpf(), req.pixKey());
        return ResponseEntity.ok(created);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@Valid @RequestBody MoneyRequest req) {
        return ResponseEntity.ok(service.deposit(req.accountId(), req.amount()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@Valid @RequestBody MoneyRequest req) {
        return ResponseEntity.ok(service.withdraw(req.accountId(), req.amount()));
    }

    @PostMapping("/pix")
    public ResponseEntity<Void> pix(@Valid @RequestBody PixTransferRequest req) {
        service.pixTransfer(req.fromPixKey(), req.toPixKey(), req.amount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> transactions(@PathVariable UUID accountId) {
        return ResponseEntity.ok(service.listTransactions(accountId));
    }
}
