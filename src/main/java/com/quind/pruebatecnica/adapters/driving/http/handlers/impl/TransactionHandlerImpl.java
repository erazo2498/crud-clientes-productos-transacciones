package com.quind.pruebatecnica.adapters.driving.http.handlers.impl;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransactionRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransferTransactionRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.ITransactionHandler;
import com.quind.pruebatecnica.adapters.driving.http.mapper.ITransactionRequestMapper;
import com.quind.pruebatecnica.domain.api.ITransactionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionHandlerImpl implements ITransactionHandler {
    private final ITransactionServicePort transactionalServicePort;
    private final ITransactionRequestMapper transactionalRequestMapper;

    @Override
    public void createTransaction(TransactionRequestDto transactionRequestDto) {
        transactionalServicePort.createTransaction(transactionalRequestMapper.toTransaction(transactionRequestDto));
    }

    @Override
    public void createTransferTransaction(TransferTransactionRequestDto transactionRequestDto) {
        transactionalServicePort.createTransaction(transactionalRequestMapper.toTransaction(transactionRequestDto));
    }
}
