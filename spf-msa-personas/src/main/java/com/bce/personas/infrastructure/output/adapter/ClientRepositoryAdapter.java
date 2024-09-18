package com.bce.personas.infrastructure.output.adapter;

import com.bce.personas.application.output.port.ClientRepositoryService;
import com.bce.personas.domain.ClientDo;
import com.bce.personas.infrastructure.exception.ClientNotFoundException;
import com.bce.personas.infrastructure.exception.DatabaseSavingOperationException;
import com.bce.personas.infrastructure.output.repository.IClientService;
import com.bce.personas.infrastructure.output.repository.entity.Client;
import com.bce.personas.infrastructure.output.repository.entity.Person;
import com.bce.personas.infrastructure.output.repository.mapper.ClientRepositoryMapper;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

import static com.bce.personas.infrastructure.util.Constants.INSERT_SQL;
import static com.bce.personas.infrastructure.util.Constants.UPDATE_SQL;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientRepositoryAdapter implements ClientRepositoryService {

    private final IClientService iClientService;

    private final ClientRepositoryMapper clientRepositoryMapper;

    private final DatabaseClient databaseClient; //TODO: artilugio para trabajar con entidad extends


    @NonNull
    @Override
    public Mono<Client> getClientId(Long id) {
        return iClientService.getById(id)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));
    }

    @NonNull
    @Override
    public Flux<Client> getAll() {
        return iClientService.getAll();
    }

    @NonNull
    @Override
    public Mono<Client> create(@Valid ClientDo client) {
        return customPerson(client)
                .flatMap(person -> iClientService.registrar(clientRepositoryMapper.toClient(client, person)))
                .map(response -> response);

    }

    @NonNull
    @Override
    public Mono<Client> update(@Valid ClientDo clientDo,
                               @NotNull Long idClient,
                               @NotNull Long idPerson
    ) {
        final var requestClient = clientRepositoryMapper.toClientUpdate(clientDo, idClient, idPerson);
        return iClientService.updateClient(requestClient);
    }

    @NonNull
    @Override
    public Mono<Person> findByCodPerson(@NotNull Long idPerson) {
        return databaseClient.sql("SELECT * FROM persona WHERE id =:code")
                .bind("code", idPerson)
                .map(MAPPING_FUNCTION)
                .first()
                .doOnError(throwable -> log.error("Error in findByCodPerson ={}", throwable.getMessage()))
                .onErrorMap(throwable -> new ClientNotFoundException());

    }

    @NonNull
    @Override
    public Mono<Person> updatePerson(
            @Valid ClientDo clientDo,
            @NotNull Long idPerson
    ) {
        return customUpdatePerson(clientDo, idPerson)
                .flatMap(this::findByCodPerson)
                .map(person -> person);
    }

    @Override
    public Mono<Void> deleteClientId(@NotNull Long id) {
        return iClientService.eliminar(id);
    }

    public static final BiFunction<Row, RowMetadata, Person> MAPPING_FUNCTION = (row, rowMetaData) -> Person.builder()
            .id(row.get("id", Long.class))
            .name(row.get("nombre", String.class))
            .gender(row.get("genero", String.class))
            .age(row.get("edad", Integer.class))
            .identification(row.get("identificacion", String.class))
            .direction(row.get("direccion", String.class))
            .phone(row.get("telefono", String.class))
            .build();


    private Mono<Long> customPerson(ClientDo client) {
        return databaseClient.sql(INSERT_SQL)
                .filter((statement, executeFunction) -> statement.returnGeneratedValues("id").execute())
                .bind("nombre", client.getName())
                .bind("genero", client.getGender())
                .bind("edad", client.getAge())
                .bind("identificacion", client.getIdentification())
                .bind("direccion", client.getDirection())
                .bind("telefono", client.getPhone())
                .fetch()
                .first()
                .map(r -> (Long) r.get("id"))
                .doOnError(throwable -> log.info("Error register client ={}", throwable.getMessage()))
                .onErrorMap(throwable -> new DatabaseSavingOperationException());
    }

    private Mono<Long> customUpdatePerson(ClientDo client, Long id) {
        return databaseClient.sql(UPDATE_SQL)
                .bind("nombre", client.getName())
                .bind("genero", client.getGender())
                .bind("edad", client.getAge())
                .bind("identificacion", client.getIdentification())
                .bind("direccion", client.getDirection())
                .bind("telefono", client.getPhone())
                .bind("id", id)
                .fetch()
                .rowsUpdated()
                .doOnError(throwable -> log.info("Error update client ={}", throwable.getMessage()))
                .onErrorMap(throwable -> new DatabaseSavingOperationException());
    }


}
