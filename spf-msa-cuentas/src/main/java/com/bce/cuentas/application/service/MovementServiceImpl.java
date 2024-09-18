package com.bce.cuentas.application.service;


import com.bce.cuentas.application.input.port.MovementService;
import com.bce.cuentas.application.output.port.AccountRepositoryService;
import com.bce.cuentas.application.output.port.MovementRepositoryService;
import com.bce.cuentas.domain.AccountDo;
import com.bce.cuentas.domain.AccountStateReport;
import com.bce.cuentas.domain.MovementDo;
import com.bce.cuentas.domain.enums.TipoMovimientoEnum;
import com.bce.cuentas.infrastructure.exception.TransactionException;
import com.bce.cuentas.infrastructure.input.adapter.rest.mapper.MovementMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovementServiceImpl implements MovementService {

    private final AccountRepositoryService accountRepositoryService;

    private final MovementRepositoryService movementRepositoryService;

    private final MovementMapper movementMapper;

    /*@NonNull
    @Override
    public Mono<MovementDo> createMovement(@Valid MovementDo movementDo) {
        return accountRepositoryService.getAccountById(movementDo.getIdCuenta().longValue())
                .flatMap(accountDo -> {
                   if (TipoMovimientoEnum.D.getDescripcion().equals(movementDo.getTipoMovimiento().getDescripcion()) && movementDo.getValor().compareTo(BigDecimal.ZERO) >= 0) {
                        depositMoney(accountDo, movementDo);
                    } else if (TipoMovimientoEnum.R.getDescripcion().equals(movementDo.getTipoMovimiento().getDescripcion()) && movementDo.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                        withdrawMoney(accountDo, movementDo);
                    } else
                        return Mono.error(new TransactionException("Tipo de transacción no valida"));

                    return accountRepositoryService.postAccount(accountDo)
                            .thenReturn(accountDo)
                            .flatMap(request -> {
                                log.info("|-->Movement start save");
                                return movementRepositoryService.addTransaction(movementDo, accountDo.getInitialBalance())
                                        .map(movementMapper::toMovementDo);
                            });

                });
    }*/
    @NonNull
    @Override
    public Mono<MovementDo> createMovement(@Valid MovementDo movementDo) {
        return accountRepositoryService.getAccountById(movementDo.getIdCuenta().longValue())
                .flatMap(accountDo -> {
                    return Optional.of(movementDo)
                            .map(movement -> {
                                final var typeMovement = movement.getTipoMovimiento();
                                final var valor = movement.getValor();
                                return processTransaction(accountDo, typeMovement, valor, movement)
                                        .flatMap(accountRepositoryService::postAccount)
                                        .flatMap(request -> {
                                            log.info("|-->Movement start save");
                                            return movementRepositoryService.addTransaction(movementDo, request.getInitialBalance())
                                                    .map(movementMapper::toMovementDo);
                                        });
                            })
                            .orElse(Mono.error(new TransactionException("Movimiento no puede ser nulo")));
                });
    }

    private Mono<AccountDo> processTransaction(AccountDo accountDo, TipoMovimientoEnum tipoMovimiento, BigDecimal
            valor, MovementDo movement) {
        return switch (tipoMovimiento) {
            case D ->
                    (valor.compareTo(BigDecimal.ZERO) >= 0) ? depositMoney(accountDo, movement).map(accountDo1 -> accountDo1)
                            : Mono.error(new TransactionException("Valor de depósito no válido"));
            case R ->
                    (valor.compareTo(BigDecimal.ZERO) <= 0) ? withdrawMoney(accountDo, movement).map(accountDo1 -> accountDo1)
                            : Mono.error(new TransactionException("Valor de retiro no válido"));
            default -> Mono.error(new TransactionException("Tipo de transacción no válida"));
        };
    }

    /*private static final BiFunction<AccountDo, MovementDo, Mono<AccountDo>>
            proccessDeposit = (account, movement) ->*/

    private Mono<AccountDo> depositMoney(AccountDo account, MovementDo transaction) {
        return Mono.fromSupplier(() -> {
            account.setInitialBalance(account.getInitialBalance().add(transaction.getValor()));
            return account;
        });
    }

    private Mono<AccountDo> withdrawMoney(AccountDo account, MovementDo transaction) {
        return Mono.defer(() -> {
            BigDecimal newBalance = account.getInitialBalance().add(transaction.getValor());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                return Mono.error(new TransactionException("Saldo no disponible"));
            }
            account.setInitialBalance(newBalance);
            return Mono.just(account);
        });
    }


    @NonNull
    @Override
    public Flux<MovementDo> getAll() {
        return movementRepositoryService.getAll()
                .map(movementMapper::toMovementDo);
    }

    @NonNull
    @Override
    public Flux<AccountStateReport> reportXUserAndDate(String identification,
                                                       LocalDate fechaDesde,
                                                       LocalDate fechaHasta
    ) {
        return movementRepositoryService.reportXUserAndDate(identification, fechaDesde, fechaHasta);
    }

}
