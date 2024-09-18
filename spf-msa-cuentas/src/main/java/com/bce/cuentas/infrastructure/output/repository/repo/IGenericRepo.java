
package com.bce.cuentas.infrastructure.output.repository.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenericRepo<T, ID> extends ReactiveCrudRepository<T, ID> {
}

