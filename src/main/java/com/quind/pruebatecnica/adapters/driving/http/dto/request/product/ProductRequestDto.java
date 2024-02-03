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
    private Long id;
    @NotBlank(message = "El tipo de accountNumber no puede ser nulo o vacio")
    @Pattern(regexp = "^(33|53)\\d{8}$", message = "El número de las cuentas debe iniciar en '33 o 53' y tener 10 dígitos numéricos.")
    private String accountNumber;
    @NotBlank(message = "El tipo de status no puede ser nulo o vacio")
    private String status;
    @Positive
    private BigDecimal balance;
    @NotNull
    private Boolean exemptGMF;
    @Positive
    private Long customerId;
}
