package com.bce.cuentas.infrastructure.output.repository.mapper;

import com.bce.cuentas.domain.MovementDo;
import com.bce.cuentas.infrastructure.output.repository.entity.Movements;
import jakarta.validation.constraints.NotBlank;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface MovementRepositoryMapper {
    String FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Mappings({
            @Mapping(target = "idMovimiento", ignore = true),
            @Mapping(target = "fecha", source = "now", qualifiedByName = "toOffsetDateTime"),
            @Mapping(target = "tipoMovimiento", source = "movementDo.tipoMovimiento"),
            @Mapping(target = "valor", source = "movementDo.valor"),
            @Mapping(target = "saldo", source = "balance"),
            @Mapping(target = "idCuenta", source = "movementDo.idCuenta"),
            @Mapping(target = "cuenta", ignore = true),

    })
    Movements toMovements(MovementDo movementDo, @NotBlank BigDecimal balance, String now);

    @Named("toOffsetDateTime")
    default OffsetDateTime toOffsetDateTime(final String stringDate) {
        ZoneId zoneId = ZoneId.of("UTC");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        LocalDateTime dateTime = LocalDateTime.parse(stringDate, formatter);
        ZoneOffset offset = zoneId.getRules().getOffset(dateTime);
        return OffsetDateTime.of(dateTime, offset);
    }
}
