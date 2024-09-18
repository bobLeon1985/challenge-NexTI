/**
 *
 */
package com.bce.cuentas.infrastructure.output.repository.entity;


import com.bce.cuentas.domain.enums.TipoMovimientoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Table(name = "movimientos")
public class Movements {

    @Id
    @Column("id")
    private Long idMovimiento;

    @Column("fecha")
    private OffsetDateTime fecha;

    @Column("tipo_movimiento")
    private TipoMovimientoEnum tipoMovimiento;

    @Column("valor")
    private BigDecimal valor;

    @Column("saldo")
    private BigDecimal saldo;

    @Column("id_cuenta")
    private Long idCuenta;

    @Transient
    private Account cuenta;


}
