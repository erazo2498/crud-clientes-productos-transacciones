package com.quind.pruebatecnica.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProductResponseDto {
    private Long id;
    private String accountType;
    private String accountNumber;
    private String status;
    private BigDecimal balance;
    private Boolean exemptGMF;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long customerId;
}
