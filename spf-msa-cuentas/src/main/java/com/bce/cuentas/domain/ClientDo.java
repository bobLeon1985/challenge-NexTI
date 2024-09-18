package com.bce.cuentas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDo {

    @JsonIgnore
    Long idCliente;

    String nombres;

    String genero;

    Integer edad;

    String identificacion;

    String direccion;

    String telefono;

    String contrasenia;

    Boolean estado;

}
