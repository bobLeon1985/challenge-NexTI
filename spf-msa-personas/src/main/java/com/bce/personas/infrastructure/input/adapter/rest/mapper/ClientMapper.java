package com.bce.personas.infrastructure.input.adapter.rest.mapper;

import com.bce.personas.domain.ClientDo;
import com.bce.personas.infrastructure.output.repository.entity.Client;
import com.bce.personas.infrastructure.output.repository.entity.Person;
import com.bce.services.server.models.ClienteDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface ClientMapper {
    @Mappings({
            @Mapping(target = "id", source = "client.clientId"),
            @Mapping(target = "name", source = "person.name"),
            @Mapping(target = "gender", source = "person.gender"),
            @Mapping(target = "age", source = "person.age"),
            @Mapping(target = "identification", source = "person.identification"),
            @Mapping(target = "direction", source = "person.direction"),
            @Mapping(target = "phone", source = "person.phone"),
            @Mapping(target = "password", source = "client.password"),
            @Mapping(target = "state", source = "client.state"),
    })
    ClientDo toClientDo(Client client, Person person);

    @Mappings({
            @Mapping(target = "idclient", source = "id"),
            @Mapping(target = "nombres", source = "name"),
            @Mapping(target = "genero", source = "gender"),
            @Mapping(target = "edad", source = "age"),
            @Mapping(target = "identificacion", source = "identification"),
            @Mapping(target = "direccion", source = "direction"),
            @Mapping(target = "telefono", source = "phone"),
            @Mapping(target = "contrasenia", source = "password"),
            @Mapping(target = "estado", source = "state"),
    })
    ClienteDTO toClienteDto(ClientDo clientDo);



    @Mappings({
            @Mapping(target = "name", source = "nombres"),
            @Mapping(target = "gender", source = "genero"),
            @Mapping(target = "age", source = "edad"),
            @Mapping(target = "identification", source = "identificacion"),
            @Mapping(target = "direction", source = "direccion"),
            @Mapping(target = "phone", source = "telefono"),
            @Mapping(target = "password", source = "contrasenia"),
            @Mapping(target = "state", source = "estado"),
    })
    ClientDo toClientDo(ClienteDTO clienteDTO);
    /*@Mappings({
            @Mapping(target = "nombres", source = "name"),
            @Mapping(target = "genero", source = "gender"),
            @Mapping(target = "edad", source = "age"),
            @Mapping(target = "identificacion", source = "identification"),
            @Mapping(target = "direccion", source = "direction"),
            @Mapping(target = "telefono", source = "phone"),
            @Mapping(target = "contrasenia", source = "password"),
            @Mapping(target = "estado", source = "state"),
    })
    Client toClient(ClientDo clientDo);*/
}
