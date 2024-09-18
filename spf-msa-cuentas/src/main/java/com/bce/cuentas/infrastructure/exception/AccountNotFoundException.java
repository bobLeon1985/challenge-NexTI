package com.bce.cuentas.infrastructure.exception;

public class AccountNotFoundException extends CodeException {
    public AccountNotFoundException() {
        super(404, "The account was not found");
    }
}