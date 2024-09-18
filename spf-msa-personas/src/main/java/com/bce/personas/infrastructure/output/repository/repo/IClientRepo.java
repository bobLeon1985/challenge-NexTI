package com.bce.personas.infrastructure.output.repository.repo;

import com.bce.personas.infrastructure.output.repository.entity.Client;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IClientRepo extends IGenericRepo<Client, Long> {
    @Query("UPDATE cliente SET contrasena =:contrasenia , estado=:status WHERE cliente_id=:id")
    Mono<Client> updateClient(Long id, String contrasenia, Boolean status);
}
