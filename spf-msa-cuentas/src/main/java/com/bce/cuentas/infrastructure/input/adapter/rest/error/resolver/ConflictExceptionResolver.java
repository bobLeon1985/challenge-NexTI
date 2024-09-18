package com.bce.cuentas.infrastructure.input.adapter.rest.error.resolver;

import com.bce.cuentas.infrastructure.util.ErrorUtils;
import com.bce.services.server.models.Error;
import org.springframework.http.HttpStatus;

public class ConflictExceptionResolver extends ErrorResolver {

    @Override
    protected int status() {
        return HttpStatus.CONFLICT.value();
    }

    @Override
    protected Error buildError(final String requestPath,
                               final Throwable throwable,
                               final String version
    ) {
        return new Error()
                .title("Conflict")
                .detail(throwable.getMessage())
                .instance(ErrorUtils.buildErrorCode(status()))
                .type(requestPath);
    }


}
