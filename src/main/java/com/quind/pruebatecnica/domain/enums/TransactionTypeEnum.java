package com.quind.pruebatecnica.domain.enums;

import com.quind.pruebatecnica.domain.exceptions.InvalidValueException;
import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import com.quind.pruebatecnica.domain.strategy.ITransactionStrategy;
import com.quind.pruebatecnica.domain.strategy.impl.ConsignmentStrategyImpl;
import com.quind.pruebatecnica.domain.strategy.impl.TransferStrategyImpl;
import com.quind.pruebatecnica.domain.strategy.impl.WithdrawalStrategyImpl;

import java.util.EnumMap;

public enum TransactionTypeEnum {
    CONSIGNMENT("C"), WITHDRAWAL("R"), TRANSFER("T");

    private static final EnumMap<TransactionTypeEnum, ITransactionStrategy> ENUM_MAP_TRANSACTION = new EnumMap<>(TransactionTypeEnum.class);

    private final String type;

    TransactionTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    static {
        ENUM_MAP_TRANSACTION.put(TransactionTypeEnum.CONSIGNMENT,new ConsignmentStrategyImpl());
        ENUM_MAP_TRANSACTION.put(TransactionTypeEnum.WITHDRAWAL,new WithdrawalStrategyImpl());
        ENUM_MAP_TRANSACTION.put(TransactionTypeEnum.TRANSFER,new TransferStrategyImpl());
    }

    public static void executeTransactionStrategy(Transaction transaction, IProductPersistencePort productPersistencePort) {
        ENUM_MAP_TRANSACTION.get(getType(transaction.getType())).executeStrategic(transaction,productPersistencePort);
    }

    public static TransactionTypeEnum getType(String description) {
        for (TransactionTypeEnum type : TransactionTypeEnum.values()) {
            if (type.getType().equals(description)) {
                return type;
            }
        }
        throw new InvalidValueException("Tipo de transacción invalida");
    }
}
