package com.bce.cuentas.infrastructure.input.adapter.rest.error.resolver;


import com.bce.cuentas.infrastructure.exception.CodeException;
import com.bce.cuentas.infrastructure.util.ErrorUtils;
import com.bce.services.server.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class CodeExceptionResolver extends ErrorResolver<Error> {
    @Override
    protected int status() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @NonNull
    @Override
    protected Error buildError(
            @NonNull final String requestPath,
            @NonNull final Throwable throwable,
            @NonNull final String version
    ) {
        final var exception = (CodeException) throwable;
        return new Error()
                .title("Unexpected error")
                .detail(exception.getMessage())
                .instance(ErrorUtils.buildErrorCode(exception.getCode()))
                .type(requestPath);
    }
}
