package com.bce.personas.infrastructure.input.adapter.rest.mapper;

import com.bce.personas.domain.User;
import com.bce.services.server.models.PostUserLoginRequest;
import com.bce.services.server.models.PostUserLoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    User toUser(PostUserLoginRequest postUserLoginRequest);

    PostUserLoginResponse toPostUserLoginResponse(String token);
}
