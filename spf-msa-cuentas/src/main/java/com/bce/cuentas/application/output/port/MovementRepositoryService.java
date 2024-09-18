package com.bce.cuentas.application.output.port;

import com.bce.cuentas.domain.AccountStateReport;
import com.bce.cuentas.domain.MovementDo;
import com.bce.cuentas.infrastructure.output.repository.entity.Movements;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
public interface MovementRepositoryService {
    @NonNull
    Mono<Movements> addTransaction(@Valid MovementDo movementDo, BigDecimal balance);

    @NonNull
    Flux<Movements> getAll();

    @NonNull
    Flux<AccountStateReport> reportXUserAndDate(
            String identification,
            LocalDate fechaDesde,
            LocalDate fechaHasta
    );

}
