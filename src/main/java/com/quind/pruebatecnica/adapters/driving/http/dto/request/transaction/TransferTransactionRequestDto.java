package com.quind.pruebatecnica.adapters.driving.http.dto.request.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TransferTransactionRequestDto {
    @NotNull
    @Pattern(regexp = "^T$",message = "el tipo de transaccion debe T, de transferencia")
    private String type;

    @NotNull(message = "Debe asignar un valor")
    @Positive(message = "debe ser un valor positivo")
    private BigDecimal value;

    @NotNull(message = "la cuenta de origen es obligatoria")
    @Positive(message = "debe ser un valor positivo")
    private Long originProductId;

    @NotNull(message = "la cuenta de destino es obligatorio")
    @Positive(message = "debe ser un valor positivo")
    private Long destinationProductId;
}
