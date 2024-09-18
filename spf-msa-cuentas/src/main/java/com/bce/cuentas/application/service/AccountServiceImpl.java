package com.bce.cuentas.application.service;

import com.bce.cuentas.application.input.port.AccountService;
import com.bce.cuentas.application.output.port.AccountRepositoryService;
import com.bce.cuentas.application.output.port.ClientService;
import com.bce.cuentas.domain.AccountDo;
import com.bce.cuentas.infrastructure.exception.AccountNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepositoryService accountRepositoryService;

    private final ClientService clientService;

    @NonNull
    @Override
    public Mono<AccountDo> getAccountById(Long id) {
        return accountRepositoryService.getAccountById(id)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()));
    }

    @NonNull
    @Override
    public Flux<AccountDo> getAll() {
        return accountRepositoryService.getAll();
    }

    @NonNull
    @Override
    public Mono<AccountDo> postAccount(@NotNull @Valid AccountDo accountDo) {
        return clientService.getClientById(accountDo.getIdClient().longValue())
                .flatMap(clientDo -> accountRepositoryService.postAccount(accountDo))
                .doOnError(throwable -> log.info("<--| Error en post account error={}", throwable.getMessage()));


    }


}
