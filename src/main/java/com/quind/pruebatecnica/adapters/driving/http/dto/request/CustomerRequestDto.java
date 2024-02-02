package com.quind.pruebatecnica.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CustomerRequestDto {
    private String identificationNumber;
    private String identificationType;
    private String name;
    private String surname;
    private String mail;
    private LocalDate birthday;
}
