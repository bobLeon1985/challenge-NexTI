package com.bce.personas.infrastructure.output.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Column("id")
    private Long id;

    @Column("nombre")
    private String name;

    @Column("genero")
    private String gender;

    @Column("edad")
    private Integer age;

    @Column("identificacion")
    private String identification;

    @Column("direccion")
    private String direction;

    @Column("telefono")
    private String phone;

}
