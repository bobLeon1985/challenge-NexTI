package com.bce.personas.infrastructure.output.repository;

import com.bce.personas.infrastructure.output.repository.entity.Client;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

public interface IClientService extends ICRUD<Client, Long> {

    @NonNull
    Mono<Client> updateClient(@Valid Client requestClient);
}
