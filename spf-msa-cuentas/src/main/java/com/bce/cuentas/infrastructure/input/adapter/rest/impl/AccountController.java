package com.bce.cuentas.infrastructure.input.adapter.rest.impl;

import com.bce.cuentas.application.input.port.AccountService;
import com.bce.cuentas.infrastructure.input.adapter.rest.mapper.AccountMapper;
import com.bce.services.server.CuentasApi;
import com.bce.services.server.models.CuentaDTO;
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
public class AccountController implements CuentasApi {

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    @Override
    public Mono<ResponseEntity<Flux<CuentaDTO>>> consultarCuentas(ServerWebExchange exchange) {
        return accountService.getAll()
                .map(accountMapper::toCuentaDto)
                .collectList()
                .map(Flux::fromIterable)
                .map(accountDo -> ResponseEntity.ok().body(accountDo));
    }

    @Override
    public Mono<ResponseEntity<Void>> crearCuenta(Mono<CuentaDTO> cuentaDTO, ServerWebExchange exchange) {
        return cuentaDTO.flatMap(account -> accountService.postAccount(accountMapper.toAccountDo(account)))
                .map(response -> ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<CuentaDTO>> consultarxid(Integer id, ServerWebExchange exchange) {
        return accountService.getAccountById(id.longValue())
                .map(accountMapper::toCuentaDto)
                .map(cuentaDTO -> ResponseEntity.ok().body(cuentaDTO));
    }

}
