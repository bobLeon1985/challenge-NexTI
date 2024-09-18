package com.bce.personas.infrastructure.output.repository.mapper;

import com.bce.personas.domain.ClientDo;
import com.bce.personas.infrastructure.output.repository.entity.Client;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface ClientRepositoryMapper {

    @Mappings({
            @Mapping(target = "password", source = "clientDo.password"),
            @Mapping(target = "personaId", source = "personId"),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "gender", ignore = true),
            @Mapping(target = "age", ignore = true),
            @Mapping(target = "identification", ignore = true),
            @Mapping(target = "direction", ignore = true),
            @Mapping(target = "phone", ignore = true),
    })
    Client toClient(ClientDo clientDo, Long personId);

    @Mappings({
            @Mapping(target = "clientId", source = "idClient"),
            @Mapping(target = "password", source = "clientDo.password"),
            @Mapping(target = "state", source = "clientDo.state"),
            @Mapping(target = "personaId", source = "idPerson"),
    })
    Client toClientUpdate(ClientDo clientDo, Long idClient, Long idPerson);
}
