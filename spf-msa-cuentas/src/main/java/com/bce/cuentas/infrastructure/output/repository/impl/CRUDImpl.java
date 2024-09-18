package com.bce.cuentas.infrastructure.output.repository.impl;

import com.bce.cuentas.infrastructure.output.repository.ICRUD;
import com.bce.cuentas.infrastructure.output.repository.repo.IGenericRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {
    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public Mono<T> registrar(T t) {
        return getRepo().save(t);
    }

    @Override
    public Mono<T> modificar(T t) {
        return null;
    }

    @Override
    public Flux<T> getAll() {
        return getRepo().findAll();
    }

    @Override
    public Mono<T> getById(ID id) {
        return getRepo().findById(id);
    }

    @Override
    public Mono<Void> eliminar(ID id) {
        return null;
    }
}


