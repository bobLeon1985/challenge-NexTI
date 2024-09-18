package com.bce.cuentas.infrastructure.output.adapter;

import com.bce.cuentas.application.output.port.AccountRepositoryService;
import com.bce.cuentas.domain.AccountDo;
import com.bce.cuentas.infrastructure.output.repository.IAccountService;
import com.bce.cuentas.infrastructure.output.repository.mapper.AccountRepositoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountRepositoryAdapter implements AccountRepositoryService {

    private final IAccountService iAccountService;

    private final AccountRepositoryMapper accountRepositoryMapper;

    @NonNull
    @Override
    public Mono<AccountDo> getAccountById(Long id) {
        return iAccountService.getById(id)
                .map(accountRepositoryMapper::toAccountDo);
    }

    @NonNull
    @Override
    public Flux<AccountDo> getAll() {
        return iAccountService.getAll()
                .map(accountRepositoryMapper::toAccountDo);

    }

    @NonNull
    @Override
    public Mono<AccountDo> postAccount(@Valid AccountDo accountDo) {
        return iAccountService.registrar(accountRepositoryMapper.toAccount(accountDo))
                .map(accountRepositoryMapper::toAccountDo);
    }

    /*@NonNull
    @Override
    public Mono<Account> postAccountT(@Valid AccountDo accountDo) {
        return iAccountService.registrar(accountRepositoryMapper.toAccount(accountDo))
                .flatMap(account -> Mono.empty());
    }*/
}
