package com.quind.pruebatecnica.adapters.driving.http.handlers;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransactionRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransferTransactionRequestDto;

public interface ITransactionHandler {
    void createTransaction(TransactionRequestDto transactionRequestDto);
    void createTransferTransaction(TransferTransactionRequestDto transactionRequestDto);

}
