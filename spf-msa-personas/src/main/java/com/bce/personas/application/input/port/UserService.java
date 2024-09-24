package com.bce.personas.application.input.port;

import com.bce.personas.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Validated
public interface UserService {
    @NonNull
    Mono<String> login(@NotNull @Valid User user);
}
