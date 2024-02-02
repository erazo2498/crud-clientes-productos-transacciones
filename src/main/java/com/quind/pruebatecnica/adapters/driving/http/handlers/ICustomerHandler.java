package com.quind.pruebatecnica.adapters.driving.http.handlers;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.CustomerRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.RequestUpdateCustomerDto;

public interface ICustomerHandler {
    void createCustomer(CustomerRequestDto customerRequestDto);
    void updateCustomer(RequestUpdateCustomerDto customerRequestDto);
    void deleteCustomerById(Long id);
}
