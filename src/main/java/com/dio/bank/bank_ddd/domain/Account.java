package com.dio.bank.bank_ddd.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String pixKey;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @CreationTimestamp
    private Instant createdAt;

    public static Account open(AccountType type, String holderName, String cpf, String pixKey) {
        return Account.builder()
                .type(type)
                .holderName(holderName)
                .cpf(cpf)
                .pixKey(pixKey)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public void deposit(BigDecimal amount) {
        validatePositive(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        validatePositive(amount);
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
    }

    private void validatePositive(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
    }
}
