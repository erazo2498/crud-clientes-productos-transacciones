package com.quind.pruebatecnica.adapters.driving.http.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductRequestDto {
    @NotBlank(message = "El tipo de cuenta no puede ser nulo o vacio")
    @Pattern(regexp = "^[AC]$",message = "el tipo de cuenta debe ser A ó C, de ahorro o corriente")
    private String accountType;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private Boolean exemptGMF;
    @Positive
    private Long customerId;
}
