package com.dio.bank.bank_ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// swagger
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
    info = @Info(
        title = "DIO Bank DDD API",
        version = "0.0.1",
        description = "Bank backend with Accounts, PIX and Investments (DDD).",
        contact = @Contact(name = "Alexandre", email = "dev@example.com")
    ),
    tags = {
        @Tag(name = "Account", description = "Account creation, deposit, withdraw, PIX and transactions"),
        @Tag(name = "Investment", description = "Create investments for an account")
    }
)
@SpringBootApplication
public class BankDddApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDddApplication.class, args);
	}

}
