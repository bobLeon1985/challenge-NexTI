package com.bce.personas.infrastructure.output.adapter;

import com.bce.personas.infrastructure.output.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceAdapter implements JwtService {
    private final JwtProperties jwtProperties;

    @NonNull
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecret()));
    }

    @NonNull
    @Override
    public Mono<String> generateJwt(@NonNull final Claims claims) {
        return Mono.just(
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(LocalDateTime.now().plusSeconds(jwtProperties.getExpiration()).toInstant(ZoneOffset.UTC)))
                        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                        .compact()
        );
    }
}
