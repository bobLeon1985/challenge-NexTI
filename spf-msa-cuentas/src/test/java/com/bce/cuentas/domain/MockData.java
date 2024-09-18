package com.bce.cuentas.domain;


import com.bce.cuentas.domain.enums.TipoCuentaEnum;
import com.bce.services.server.models.CuentaDTO;

import java.math.BigDecimal;

@SuppressWarnings("java:S1118")
public class MockData {
    public static AccountDo buildAccountDo() {
        return AccountDo.builder()
                .idCuenta(1L)
                .accountNumber("123456")
                .accountType(TipoCuentaEnum.A)
                .initialBalance(BigDecimal.valueOf(200.00))
                .status(Boolean.TRUE)
                .idClient(1)
                .build();

    }

    public static CuentaDTO buildCuentaDTO() {
        return new CuentaDTO()
                .idCuenta(1)
                .numeroCuenta("123456")
                .tipoCuenta(CuentaDTO.TipoCuentaEnum.A)
                .saldoInicial(BigDecimal.valueOf(200))
                .estado(Boolean.TRUE)
                .idCliente(1);

    }
}
