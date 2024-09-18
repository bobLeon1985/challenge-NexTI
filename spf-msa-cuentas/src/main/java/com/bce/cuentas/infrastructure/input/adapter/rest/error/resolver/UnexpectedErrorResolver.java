package com.bce.cuentas.infrastructure.input.adapter.rest.error.resolver;

import com.bce.cuentas.infrastructure.util.ErrorUtils;
import com.bce.services.server.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class UnexpectedErrorResolver extends ErrorResolver<Error> {
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
        return new Error()
                .title("Unexpected error")
                .detail(throwable.getMessage())
                .instance(ErrorUtils.buildErrorCode(status()))
                .type(requestPath);
    }
}
