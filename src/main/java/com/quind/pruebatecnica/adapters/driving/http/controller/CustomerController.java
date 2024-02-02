package com.quind.pruebatecnica.adapters.driving.http.controller;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.CustomerRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.ICustomerHandler;
import com.quind.pruebatecnica.configuration.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerHandler customerHandler;

    @PostMapping
    public ResponseEntity<Map<String,String>> saveCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        customerHandler.createCustomer(customerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CUSTOMER_CREATED_MESSAGE));
    }
}
