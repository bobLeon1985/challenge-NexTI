package com.bce.cuentas.application.output.port;

import com.bce.cuentas.domain.AccountDo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface AccountRepositoryService {

    @NonNull
    Mono<AccountDo> getAccountById(@NotNull Long id);
    @NonNull
    Flux<AccountDo> getAll();

    @NonNull
    Mono<AccountDo> postAccount(@Valid AccountDo accountDo);
}
