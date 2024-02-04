package com.quind.pruebatecnica.domain.model;

import java.math.BigDecimal;

public class Transaction {
    private Long id;
    private String type;

    private BigDecimal value;

    private Long originProductId;

    private Long destinationProductId;

    public Transaction(Long id, String type, BigDecimal value, Long originProductId, Long destinationProductId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.originProductId = originProductId;
        this.destinationProductId = destinationProductId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getOriginProductId() {
        return originProductId;
    }

    public void setOriginProductId(Long originProductId) {
        this.originProductId = originProductId;
    }

    public Long getDestinationProductId() {
        return destinationProductId;
    }

    public void setDestinationProductId(Long destinationProductId) {
        this.destinationProductId = destinationProductId;
    }
}
