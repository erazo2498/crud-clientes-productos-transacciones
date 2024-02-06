package com.quind.pruebatecnica.adapters.driving.http.mapper;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransactionRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction.TransferTransactionRequestDto;
import com.quind.pruebatecnica.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITransactionRequestMapper {

    @Mapping(source = "productId", target = "originProductId")
    Transaction toTransaction(TransactionRequestDto transactionRequestDto);
    Transaction toTransaction(TransferTransactionRequestDto transactionRequestDto);

}
