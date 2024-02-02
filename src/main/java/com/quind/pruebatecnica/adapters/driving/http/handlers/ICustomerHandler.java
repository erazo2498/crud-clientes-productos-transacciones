package com.quind.pruebatecnica.adapters.driving.http.handlers;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.CustomerRequestDto;

public interface ICustomerHandler {
    void createCustomer(CustomerRequestDto customerRequestDto);
}
