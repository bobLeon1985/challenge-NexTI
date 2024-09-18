package com.bce.cuentas.infrastructure.input.adapter.rest.impl;

import com.bce.cuentas.application.input.port.MovementService;
import com.bce.cuentas.infrastructure.input.adapter.rest.mapper.MovementMapper;
import com.bce.services.server.MovimientosApi;
import com.bce.services.server.models.AccountStateReportDto;
import com.bce.services.server.models.MovimientoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MovementsController implements MovimientosApi {

    private final MovementService movementService;

    private final MovementMapper movementMapper;

    @Override
    public Mono<ResponseEntity<MovimientoDTO>> crearMovmiento(Mono<MovimientoDTO> movimientoDTO, ServerWebExchange exchange) {
        return movimientoDTO.flatMap(movement -> {
                    final var request = movementMapper.toMovementDo(movement);
                    return movementService.createMovement(request);
                })
                .map(movementDo -> ResponseEntity.ok().body(movementMapper.toMovimientoDto(movementDo)));
    }

    @Override
    public Mono<ResponseEntity<Flux<MovimientoDTO>>> consultarMovientos(ServerWebExchange exchange) {
        return movementService.getAll()
                .map(movementMapper::toMovimientoDto)
                .collectList()
                .map(Flux::fromIterable)
                .map(movimientoDTOFlux -> ResponseEntity.ok().body(movimientoDTOFlux));
    }

    @Override
    public Mono<ResponseEntity<Flux<AccountStateReportDto>>> reportesMovimientos(String identifiacion,
                                                                                 LocalDate fechaDesde,
                                                                                 LocalDate fechaHasta,
                                                                                 ServerWebExchange exchange) {
        return movementService.reportXUserAndDate(identifiacion, fechaDesde, fechaHasta)
                .map(movementMapper::toAccountStateReportDto)
                .collectList()
                .map(Flux::fromIterable)
                .map(accountStateReportDtoFlux -> ResponseEntity.ok().body(accountStateReportDtoFlux));
    }


}
