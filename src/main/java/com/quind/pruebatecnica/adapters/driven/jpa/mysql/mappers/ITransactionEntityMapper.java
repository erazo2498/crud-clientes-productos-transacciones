package com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.TransactionEntity;
import com.quind.pruebatecnica.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITransactionEntityMapper {
    TransactionEntity toEntity(Transaction transaction);
}
