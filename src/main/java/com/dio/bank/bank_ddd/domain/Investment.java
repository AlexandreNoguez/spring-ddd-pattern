package com.dio.bank.bank_ddd.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "investments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    private InvestmentType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @CreationTimestamp
    private Instant createdAt;

    public static Investment create(UUID accountId, InvestmentType type, BigDecimal amount) {
        if (accountId == null) throw new IllegalArgumentException("accountId is required");
        if (type == null) throw new IllegalArgumentException("type is required");
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        return Investment.builder()
                .accountId(accountId)
                .type(type)
                .amount(amount)
                .build();
    }
}
