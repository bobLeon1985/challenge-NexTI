package com.bce.personas.infrastructure.input.adapter.rest.impl;

import com.bce.personas.application.input.port.ClientService;
import com.bce.personas.infrastructure.input.adapter.rest.mapper.ClientMapper;
import com.bce.services.server.ClientesApi;
import com.bce.services.server.models.ClienteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController implements ClientesApi {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Override
    public Mono<ResponseEntity<ClienteDTO>> consultarXID(Integer clientId, ServerWebExchange exchange) {
        log.info("|--> Start get client by id");
        return clientService.getClientId(clientId)
                .map(clientDo -> ResponseEntity.ok().body(clientMapper.toClienteDto(clientDo)))
                .doOnSuccess(clienteDTOResponseEntity -> log.info("<--| Get client by id success"));
    }

    @Override
    public Mono<ResponseEntity<Flux<ClienteDTO>>> consultarClientes(ServerWebExchange exchange) {
        log.info("|--> Start get all clients");
        return clientService.getAll()
                .map(clientMapper::toClienteDto)
                .collectList()
                .map(Flux::fromIterable)
                .map(clienteDTOFlux -> ResponseEntity.ok().body(clienteDTOFlux))
                .doOnSuccess(fluxResponseEntity -> log.info("<--| Get all clients success"));
    }

    @Override
    public Mono<ResponseEntity<Void>> crearCliente(Mono<ClienteDTO> clienteDTO, ServerWebExchange exchange) {
        log.info("|--> Start create client");
        return clienteDTO
                .flatMap(clientDTO -> clientService.create(clientMapper.toClientDo(clientDTO)))
                .map(clientDo -> ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> actualizarCliente(Integer id, Mono<ClienteDTO> clienteDTO, ServerWebExchange exchange) {
        log.info("|--> Start update client");
        return clienteDTO
                .flatMap(clienteDo -> clientService.update(id.longValue(), clientMapper.toClientDo(clienteDo)))
                .map(clientDo -> ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> eliminarCliente(Integer id, ServerWebExchange exchange) {
        return clientService.deleteClientId(id)
                .map(empty -> ResponseEntity.ok().build());
    }
}
