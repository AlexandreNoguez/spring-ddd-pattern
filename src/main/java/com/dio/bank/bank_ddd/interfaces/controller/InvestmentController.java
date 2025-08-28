package com.dio.bank.bank_ddd.interfaces.controller;

import com.dio.bank.bank_ddd.application.InvestmentService;
import com.dio.bank.bank_ddd.domain.Investment;
import com.dio.bank.bank_ddd.interfaces.dto.InvestmentDtos.CreateInvestmentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Investment")
@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService service;

    @Operation(summary = "Create an investment (withdraws balance and locks into investment)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Investment created"),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    @PostMapping
    public ResponseEntity<Investment> create(@Valid @RequestBody CreateInvestmentRequest req) {
        return ResponseEntity.ok(service.create(req.accountId(), req.type(), req.amount()));
    }
}
