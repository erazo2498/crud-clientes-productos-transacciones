package com.quind.pruebatecnica.domain.spi;

import com.quind.pruebatecnica.domain.model.Transaction;

public interface ITransactionPersistencePort {
    void createTransaction(Transaction transaction);
}
