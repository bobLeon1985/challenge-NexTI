package com.bce.personas.infrastructure.input.adapter.rest.impl;


import com.bce.personas.application.input.port.UserService;
import com.bce.personas.infrastructure.input.adapter.rest.mapper.UserMapper;
import com.bce.services.server.UsersApi;
import com.bce.services.server.models.PostUserLoginRequest;
import com.bce.services.server.models.PostUserLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UsersApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @NonNull
    @Override
    public Mono<ResponseEntity<PostUserLoginResponse>> postUserLogin(
            @NonNull final Mono<PostUserLoginRequest> postUserLoginRequest,
            final ServerWebExchange exchange
    ) {
        return postUserLoginRequest.flatMap(request -> {
            final var username = request.getUsername();
            log.info("|-> UX postUserLogin started: username={}", username);
            return userService.login(userMapper.toUser(request))
                    .map(userMapper::toPostUserLoginResponse)
                    .doOnSuccess(response -> log.info("<-| UX postUserLogin finished successfully: username={}", username))
                    .doOnError(error -> log.error("<-| UX postUserLogin finished with error: username={}", username, error))
                    .map(ResponseEntity::ok);
        });
    }


}
