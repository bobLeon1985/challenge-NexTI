package com.bce.cuentas.infrastructure.output.repository.impl;

import com.bce.cuentas.infrastructure.output.repository.ITransactionService;
import com.bce.cuentas.infrastructure.output.repository.entity.Movements;
import com.bce.cuentas.infrastructure.output.repository.repo.IGenericRepo;
import com.bce.cuentas.infrastructure.output.repository.repo.ITransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionRepoImpl extends CRUDImpl<Movements, Long> implements ITransactionService {
    private final ITransactionRepo iTransactionRepo;

    @Override
    protected IGenericRepo<Movements, Long> getRepo() {
        return iTransactionRepo;
    }
}
