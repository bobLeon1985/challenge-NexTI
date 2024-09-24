package com.bce.personas.infrastructure.output.adapter;


import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface JwtService {
    @NonNull
    Mono<String> generateJwt(@NonNull final Claims claims);
}
