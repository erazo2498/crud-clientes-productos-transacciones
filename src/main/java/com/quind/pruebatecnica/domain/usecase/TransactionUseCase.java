package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.domain.api.ITransactionServicePort;
import com.quind.pruebatecnica.domain.enums.TransactionTypeEnum;
import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import com.quind.pruebatecnica.domain.spi.ITransactionPersistencePort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TransactionUseCase implements ITransactionServicePort {
    private final ITransactionPersistencePort transactionPersistencePort;
    private final IProductPersistencePort productPersistencePort;

    public TransactionUseCase(ITransactionPersistencePort transactionPersistencePort, IProductPersistencePort productPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        TransactionTypeEnum.executeTransactionStrategy(transaction, productPersistencePort);
        transactionPersistencePort.createTransaction(transaction);
    }

}
