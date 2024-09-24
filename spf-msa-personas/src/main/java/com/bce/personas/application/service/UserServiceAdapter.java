package com.bce.personas.application.service;

import com.bce.personas.application.input.port.UserService;
import com.bce.personas.application.output.port.UserSessionService;
import com.bce.personas.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceAdapter implements UserService {
    private final UserSessionService userSessionService;

    @NonNull
    @Override
    public Mono<String> login(@NonNull @NotNull @Valid final User user) {
        final var username = user.getUsername();
        log.info("|--> Login process started: username={}", username);
        return userSessionService.login(user)
                .doOnSuccess(token -> log.info("<--| Login process finished successfully: username={}", username))
                .doOnError(error -> log.error("<--| Login process finished with error: username={}", username));
    }
}