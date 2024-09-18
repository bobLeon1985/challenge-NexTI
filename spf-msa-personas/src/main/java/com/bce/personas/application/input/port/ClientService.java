package com.bce.personas.application.input.port;

import com.bce.personas.domain.ClientDo;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface ClientService {

    @NonNull
    Mono<ClientDo> getClientId(@NotNull Integer id);

    @NonNull
    Mono<ClientDo> create(@Valid ClientDo clientDo);

    @NonNull
    Mono<ClientDo> update(@NotNull Long id, @Valid ClientDo clientDo);

    @NonNull
    Flux<ClientDo> getAll();

    Mono<Void> deleteClientId(@NotNull Integer id);
}
