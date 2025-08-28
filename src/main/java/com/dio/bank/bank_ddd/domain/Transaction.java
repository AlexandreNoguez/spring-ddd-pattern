package com.dio.bank.bank_ddd.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private String description;

    @CreationTimestamp
    private Instant createdAt;

    public static Transaction of(UUID accountId, TransactionType type, BigDecimal amount, String description) {
        if (accountId == null || type == null || amount == null || amount.signum() <= 0)
            throw new IllegalArgumentException("Invalid transaction");
        return Transaction.builder()
                .accountId(accountId)
                .type(type)
                .amount(amount)
                .description(description)
                .build();
    }
}
