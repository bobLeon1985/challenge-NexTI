package com.bce.cuentas.infrastructure.output.adapter;

import com.bce.cuentas.application.output.port.MovementRepositoryService;
import com.bce.cuentas.domain.AccountStateReport;
import com.bce.cuentas.domain.MovementDo;
import com.bce.cuentas.infrastructure.output.repository.IMovimientoRepo;
import com.bce.cuentas.infrastructure.output.repository.ITransactionService;
import com.bce.cuentas.infrastructure.output.repository.entity.Movements;
import com.bce.cuentas.infrastructure.output.repository.mapper.MovementRepositoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovementRepositoryAdapter implements MovementRepositoryService {
    private final ITransactionService iTransactionService;
    private final MovementRepositoryMapper movementRepositoryMapper;
    private final IMovimientoRepo iMovimientoRepo;

    @NonNull
    @Override
    public Mono<Movements> addTransaction(@Valid MovementDo movementDo, BigDecimal balance) {
        final var requestT = movementRepositoryMapper.toMovements(movementDo, balance, OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return iTransactionService.registrar(requestT);
    }

    @NonNull
    @Override
    public Flux<Movements> getAll() {
        return iTransactionService.getAll();
    }
    @NonNull
    @Override
    public Flux<AccountStateReport> reportXUserAndDate(String identification, LocalDate fechaDesde, LocalDate fechaHasta) {
        return iMovimientoRepo.findEstadoCuentaByFechas(identification, fechaDesde ,fechaHasta);
    }


}
