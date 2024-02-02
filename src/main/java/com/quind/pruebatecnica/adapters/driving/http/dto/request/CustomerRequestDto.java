package com.quind.pruebatecnica.adapters.driving.http.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CustomerRequestDto {
    @NotBlank(message = "El numero de identificación no puede ser nulo o vacio")
    private String identificationNumber;
    @NotBlank(message = "El tipo de identificación no puede ser nulo o vacio")
    private String identificationType;
    @Size(message = "El nombre NO puede ser menor a 2 caracteres.", min = 2)
    @NotBlank(message = "El nombre no puede ser nulo o vacio")
    private String name;
    @Size(message = "El apellido NO puede ser menor a 2 caracteres.", min = 2)
    @NotBlank(message = "El nombre no puede ser nulo o vacio")
    private String surname;
    @Pattern(regexp = "^(\\S+@\\S+\\.\\S+)$",message = "Debe ser una dirección de correo electrónico con un formato correcto: xxxx@xxxxx.xxx")
    private String mail;
    @Past(message = "La fecha debe ser una fecha anterior a la actual")
    private LocalDate birthday;
}
