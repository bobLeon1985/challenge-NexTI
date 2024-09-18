package com.bce.cuentas.domain.enums;

import lombok.Getter;

public enum TipoMovimientoEnum {
	
	R("Retiro"), D("Deposito");

	@Getter
	private String descripcion;

	TipoMovimientoEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
