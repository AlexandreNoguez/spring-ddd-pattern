package com.dio.bank.bank_ddd.interfaces.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .servers(List.of(new Server().url("http://localhost:8080")))
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("DIO Bank DDD API")
                .version("0.0.1")
                .description("Bank backend with Accounts, PIX and Investments (DDD).")
                .license(new License().name("MIT"))
            );
    }
}
