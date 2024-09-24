package com.bce.personas.infrastructure.output.adapter;

import com.bce.personas.application.output.port.UserSessionService;
import com.bce.personas.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSessionServiceAdapter implements UserSessionService {
    private static final String UUID_CLAIM = "uuid";

    private final JwtService jwtService;

    @NonNull
    private static Mono<Claims> createUserClaims(@NonNull final User user) {
        return Mono.just(Jwts.claims().setSubject(user.getUsername()))
                .doOnSuccess(claims -> claims.put(UUID_CLAIM, UUID.randomUUID().toString()));
    }

    @NonNull
    @Override
    public Mono<String> login(@NonNull @NotNull @Valid final User user) {
        return createUserClaims(user).flatMap(jwtService::generateJwt)
                .doOnSuccess(token -> log.info("The user [{}] was logged in", user.getUsername()))
                .doOnError(error -> log.error("Error logging in the user [{}]: error={}", user.getUsername(), error.getMessage()));
    }
}
