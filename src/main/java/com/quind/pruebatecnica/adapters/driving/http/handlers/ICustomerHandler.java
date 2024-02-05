package com.quind.pruebatecnica.adapters.driving.http.handlers;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.CustomerRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.RequestUpdateCustomerDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.response.CustomerResponseDto;

public interface ICustomerHandler {
    CustomerResponseDto getCustomerById(Long customerId);
    void createCustomer(CustomerRequestDto customerRequestDto);
    void updateCustomer(RequestUpdateCustomerDto customerRequestDto);
    void deleteCustomerById(Long id);

}
