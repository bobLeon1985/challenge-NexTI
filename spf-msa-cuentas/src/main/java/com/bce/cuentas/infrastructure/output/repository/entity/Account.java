package com.bce.cuentas.infrastructure.output.repository.entity;

import com.bce.cuentas.domain.ClientDo;
import com.bce.cuentas.domain.enums.TipoCuentaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuenta")
public class Account {

    @Id
    @Column("id_cuenta")
    Long idCuenta;

    @Column("numero_cuenta")
    String numeroCuenta;

    @Column("tipo_cuenta")
    TipoCuentaEnum tipoCuenta;

    @Column("saldo_inicial")
    BigDecimal saldoInicial;

    @Column("estado")
    Boolean estado;

    @Column("cliente_id")
    Integer idCliente;

    @Transient
    ClientDo cliente;

}
