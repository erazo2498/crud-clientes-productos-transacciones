package com.quind.pruebatecnica.domain.enums;

public enum StatusEnum {
    ACTIVE("ACTIVO"),
    INACTIVE("INACTIVO"),
    CANCELLED("CANCELADO");

    private final String value;
    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
