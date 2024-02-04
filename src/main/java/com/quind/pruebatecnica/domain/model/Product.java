package com.quind.pruebatecnica.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private Long id;
    private String accountType;
    private String accountNumber;
    private String status;
    private BigDecimal balance;
    private Boolean exemptGMF;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long customerId;

    public Product(Long id, String accountType, String accountNumber, String status, BigDecimal balance, Boolean exemptGMF, LocalDateTime createdDate, LocalDateTime modifiedDate, Long customerId) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.status = status;
        this.balance = balance;
        this.exemptGMF = exemptGMF;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getExemptGMF() {
        return exemptGMF;
    }

    public void setExemptGMF(Boolean exemptGMF) {
        this.exemptGMF = exemptGMF;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
