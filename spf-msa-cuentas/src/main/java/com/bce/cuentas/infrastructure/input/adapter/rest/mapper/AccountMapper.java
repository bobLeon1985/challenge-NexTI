package com.bce.cuentas.infrastructure.input.adapter.rest.mapper;

import com.bce.cuentas.domain.AccountDo;
import com.bce.cuentas.domain.enums.TipoCuentaEnum;
import com.bce.cuentas.infrastructure.output.repository.entity.Account;
import com.bce.services.server.models.CuentaDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface AccountMapper {

    @Mappings({
            @Mapping(target = "idCuenta", ignore = true),
            @Mapping(target = "accountNumber", source = "numeroCuenta"),
            @Mapping(target = "accountType", source = "tipoCuenta"),
            @Mapping(target = "initialBalance", source = "saldoInicial"),
            @Mapping(target = "status", source = "estado"),
            @Mapping(target = "idClient", source = "idCliente"),
    })
    AccountDo toAccountDo(CuentaDTO cuentaDTO);

    default TipoCuentaEnum map(String value) {
        return TipoCuentaEnum.valueOf(value);
    }

    @Mappings({
            @Mapping(target = "idCuenta", source = "idCuenta"),
            @Mapping(target = "numeroCuenta", source = "accountNumber"),
            @Mapping(target = "tipoCuenta", source = "accountType"),
            @Mapping(target = "saldoInicial", source = "initialBalance"),
            @Mapping(target = "estado", source = "status"),
            @Mapping(target = "idCliente", source = "idClient")
    })
    CuentaDTO toCuentaDto(AccountDo accountDo);
}
