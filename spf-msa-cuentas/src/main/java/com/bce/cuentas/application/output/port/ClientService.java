package com.bce.cuentas.application.output.port;

import com.bce.cuentas.domain.ClientDo;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
public interface ClientService {
    @NonNull
    Mono<ClientDo> getClientById(@NotNull Long id);
}
