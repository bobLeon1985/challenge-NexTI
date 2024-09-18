/**
 * 
 */
package com.bce.cuentas.domain.enums;

import lombok.Getter;

/**
 * @author edwinleon
 *
 */
public enum TipoCuentaEnum {

	A("AHORRO"), C("CORRIENTE");

	@Getter
	private String descripcion;

	TipoCuentaEnum(String descripcion) {
		this.descripcion = descripcion;
	}

}
