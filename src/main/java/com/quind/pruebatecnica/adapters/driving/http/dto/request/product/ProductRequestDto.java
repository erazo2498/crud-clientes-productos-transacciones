package com.quind.pruebatecnica.adapters.driving.http.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProductRequestDto {
    private Long id;
    @NotBlank(message = "El tipo de accountType no puede ser nulo o vacio")
    private String accountType;
    @NotBlank(message = "El tipo de accountNumber no puede ser nulo o vacio")
    private String accountNumber;
    @NotBlank(message = "El tipo de status no puede ser nulo o vacio")
    private String status;
    @Positive
    private BigDecimal balance;
    @NotNull
    private Boolean exemptGMF;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @Positive
    private Long customerId;
}
