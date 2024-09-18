package com.bce.cuentas.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountStateReport {
    OffsetDateTime fecha;
    String cliente;
    String numeroCuenta;
    String tipo;
    BigDecimal saldoInicial;
    Boolean estado;
    BigDecimal movimiento;
    BigDecimal saldoDisponible;
}
