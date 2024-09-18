package com.bce.cuentas.infrastructure.output.adapter;

import com.bce.cuentas.application.output.port.ClientService;
import com.bce.cuentas.domain.ClientDo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientAdapter implements ClientService {
    private final WebClient webClient;

    @NonNull
    @Override
    public Mono<ClientDo> getClientById(@NotNull Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return webClient
                .get()
                .uri("/clientes/byid/{id}", params)
                .retrieve()
                .bodyToMono(ClientDo.class)
                .map(clientDto -> clientDto);
    }
}
