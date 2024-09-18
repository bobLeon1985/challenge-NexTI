package com.bce.personas.application.output.port;

import com.bce.personas.domain.ClientDo;
import com.bce.personas.infrastructure.output.repository.entity.Client;
import com.bce.personas.infrastructure.output.repository.entity.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface ClientRepositoryService {
    @NonNull
    Mono<Client> getClientId(@NotNull Long id);

    @NonNull
    Flux<Client> getAll();

    @NonNull
    Mono<Client> create(@Valid ClientDo clientDo);

    @NonNull
    Mono<Client> update(@Valid ClientDo clientDo,
                        @NotNull Long idClient,
                        @NotNull Long idPerson);

    @NonNull
    Mono<Person> findByCodPerson(@NotNull Long idPerson);

    @NonNull
    Mono<Person> updatePerson(@Valid ClientDo clientDo,
                              @NotNull Long idPerson);

    Mono<Void> deleteClientId(@NotNull Long id);
}
