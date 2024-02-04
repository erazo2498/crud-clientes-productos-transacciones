package com.quind.pruebatecnica.domain.enums;

public enum TransactionTypeEnum {
    DEPOSIT("D"), WITHDRAWAL("R"), TRANSFER("T");

    private final String type;

    TransactionTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
