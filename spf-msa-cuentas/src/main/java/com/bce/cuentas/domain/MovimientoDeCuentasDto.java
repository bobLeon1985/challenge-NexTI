/**
 * 
 */
package com.bce.cuentas.domain;


import com.bce.cuentas.domain.enums.TipoMovimientoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author edwinleon
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDeCuentasDto {
	private String numeroCuenta;
	private TipoMovimientoEnum tipoMovimiento;
	private BigDecimal valor;
	private Long idCuenta;
}
