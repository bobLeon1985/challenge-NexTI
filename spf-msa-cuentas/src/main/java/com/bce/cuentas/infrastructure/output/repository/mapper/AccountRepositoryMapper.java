package com.bce.cuentas.infrastructure.output.repository.mapper;

import com.bce.cuentas.domain.AccountDo;
import com.bce.cuentas.domain.enums.TipoCuentaEnum;
import com.bce.cuentas.infrastructure.output.repository.entity.Account;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface AccountRepositoryMapper {
    @Mappings({
            //@Mapping(target = "idCuenta", ignore = true),
            @Mapping(target = "numeroCuenta", source = "accountNumber"),
            @Mapping(target = "tipoCuenta", source = "accountType"),
            @Mapping(target = "saldoInicial", source = "initialBalance"),
            @Mapping(target = "estado", source = "status"),
            @Mapping(target = "idCliente", source = "idClient"),
            @Mapping(target = "cliente", ignore = true),
    })
    Account toAccount(AccountDo accountDo);

    default TipoCuentaEnum map(String value) {
        return TipoCuentaEnum.valueOf(value);
    }

    @Mappings({
            @Mapping(target = "idCuenta", source = "idCuenta"),
            @Mapping(target = "accountNumber", source = "numeroCuenta"),
            @Mapping(target = "accountType", source = "tipoCuenta"),
            @Mapping(target = "initialBalance", source = "saldoInicial"),
            @Mapping(target = "status", source = "estado"),
            @Mapping(target = "idClient", source = "idCliente")
    })
    AccountDo toAccountDo(Account account);
}
