/**
 *
 */
package com.bce.cuentas.domain;

import com.bce.cuentas.domain.enums.TipoMovimientoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author edwinleon
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovementDo {
    @JsonIgnore
    Long idMovimientos;
    OffsetDateTime fecha;
    TipoMovimientoEnum tipoMovimiento;
    BigDecimal valor;
    BigDecimal saldo;
    Integer idCuenta;
}
