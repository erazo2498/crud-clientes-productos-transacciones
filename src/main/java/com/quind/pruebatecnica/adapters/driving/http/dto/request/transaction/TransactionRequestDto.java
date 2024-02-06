package com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TransactionRequestDto {
    @NotNull
    @Pattern(regexp = "^[CR]$",message = "el tipo de transaccion debe ser C ó R, de CONSIGNACIÓN o RETIRO")
    private String type;

    @NotNull(message = "Debe asignar un valor")
    @Positive(message = "debe ser un valor positivo")
    private BigDecimal value;

    @NotNull(message = "la cuenta de origen es obligatoria")
    @Positive(message = "debe ser un valor positivo")
    private Long productId;
}
