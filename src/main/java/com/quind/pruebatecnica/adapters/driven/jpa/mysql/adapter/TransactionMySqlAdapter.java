package com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ITransactionEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ITransactionRepository;
import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.ITransactionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class TransactionMySqlAdapter implements ITransactionPersistencePort {
    private final ITransactionRepository transactionRepository;
    private final ITransactionEntityMapper transactionEntityMapper;

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transactionEntityMapper.toEntity(transaction));
    }
}
