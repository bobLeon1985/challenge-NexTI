package com.bce.personas.infrastructure.output.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;


@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "cliente")
public class Client extends Person {
    @Id
    @Column("cliente_id")
    Long clientId;

    @Column("contrasena")
    String password;

    @Column("persona_id")
    Long personaId;

    @Column("estado")
    Boolean state;

}
