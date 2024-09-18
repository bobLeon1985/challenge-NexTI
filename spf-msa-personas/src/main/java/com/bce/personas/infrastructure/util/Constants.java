package com.bce.personas.infrastructure.util;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("java:S1118")
public class Constants {
    public static final String INSERT_SQL = "INSERT INTO  persona (nombre, genero,edad,identificacion,direccion,telefono) " +
            "VALUES (:nombre, :genero, :edad, :identificacion,:direccion,:telefono)";
    public static final String UPDATE_SQL = "UPDATE persona SET nombre=:nombre , genero=:genero,edad=:edad,identificacion=:identificacion,direccion=:direccion,telefono=:telefono " +
            "WHERE id=:id ";

}
