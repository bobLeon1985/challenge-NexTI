package com.bce.cuentas.infrastructure.input.adapter.rest.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @NotBlank
    @Value("${client.http-client.baseUrl}")
    String baseUrl;

    @Bean
    public WebClient registrarWebClient() {
        return WebClient.create(baseUrl);
    }
}
