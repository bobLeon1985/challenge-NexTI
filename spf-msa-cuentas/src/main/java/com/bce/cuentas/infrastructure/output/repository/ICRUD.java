package com.bce.cuentas.infrastructure.output.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T, ID> {
    Mono<T> registrar(T t);

    Mono<T> modificar(T t);

    Flux<T> getAll();

    Mono<T> getById(ID id);

    Mono<Void> eliminar(ID id);
}
