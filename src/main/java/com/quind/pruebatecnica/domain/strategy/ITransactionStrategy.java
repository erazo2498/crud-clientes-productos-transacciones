package com.quind.pruebatecnica.domain.strategy;

import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;

public interface ITransactionStrategy {
    void executeStrategic(Transaction transaction, IProductPersistencePort productPersistencePort);
}
