package com.bce.cuentas.infrastructure.output.repository.impl;

import com.bce.cuentas.infrastructure.output.repository.IAccountService;
import com.bce.cuentas.infrastructure.output.repository.entity.Account;
import com.bce.cuentas.infrastructure.output.repository.repo.IAccountRepo;
import com.bce.cuentas.infrastructure.output.repository.repo.IGenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepoImpl extends CRUDImpl<Account, Long> implements IAccountService {
    private final IAccountRepo iAccountRepo;

    @Override
    protected IGenericRepo<Account, Long> getRepo() {
        return iAccountRepo;
    }
}
