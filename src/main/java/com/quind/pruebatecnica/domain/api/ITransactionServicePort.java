package com.quind.pruebatecnica.domain.api;

import com.quind.pruebatecnica.domain.model.Transaction;

public interface ITransactionServicePort {
    void createTransaction(Transaction transaction);
}
