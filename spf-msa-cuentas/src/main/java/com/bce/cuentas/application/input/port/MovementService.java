package com.bce.cuentas.application.input.port;

import com.bce.cuentas.domain.AccountStateReport;
import com.bce.cuentas.domain.MovementDo;

import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Validated
public interface MovementService {
    @NonNull
    Mono<MovementDo> createMovement(@Valid MovementDo movementDo);

    //Movements validaRegistroMovimiento(MovimientoDeCuentasDto movimientoDeCuentasDto);
    @NonNull
    Flux<MovementDo> getAll();

    @NonNull
    Flux<AccountStateReport> reportXUserAndDate(
            String identification,
            LocalDate fechaDesde,
            LocalDate fechaHasta
    );
}