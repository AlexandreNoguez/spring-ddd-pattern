package com.dio.bank.bank_ddd.interfaces.controller;

import com.dio.bank.bank_ddd.application.InvestmentService;
import com.dio.bank.bank_ddd.domain.Investment;
import com.dio.bank.bank_ddd.interfaces.dto.InvestmentDtos.CreateInvestmentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService service;

    @PostMapping
    public ResponseEntity<Investment> create(@Valid @RequestBody CreateInvestmentRequest req) {
        return ResponseEntity.ok(service.create(req.accountId(), req.type(), req.amount()));
    }
}
