package com.quind.pruebatecnica.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CustomerResponseDto {
    private Long id;
    private String identificationNumber;
    private String identificationType;
    private String name;
    private String surname;
    private String mail;
    private LocalDate birthday;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
