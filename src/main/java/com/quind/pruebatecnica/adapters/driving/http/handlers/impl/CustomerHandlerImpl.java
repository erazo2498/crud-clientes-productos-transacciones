package com.quind.pruebatecnica.adapters.driving.http.handlers.impl;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.CustomerRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.RequestUpdateCustomerDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.response.CustomerResponseDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.ICustomerHandler;
import com.quind.pruebatecnica.adapters.driving.http.mapper.ICustomerRequestMapper;
import com.quind.pruebatecnica.domain.api.ICustomerServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerHandlerImpl implements ICustomerHandler {
    private final ICustomerServicePort customerServicePort;
    private final ICustomerRequestMapper customerRequestMapper;

    @Override
    public CustomerResponseDto getCustomerById(Long customerId) {
        return customerRequestMapper.toCustomerResponseDto(customerServicePort.getCustomerById(customerId));
    }

    @Override
    public void createCustomer(CustomerRequestDto customerRequestDto) {
        customerServicePort.createCustomer(customerRequestMapper.toCustomer(customerRequestDto));
    }

    @Override
    public void updateCustomer(RequestUpdateCustomerDto customerRequestDto) {
        customerServicePort.updateCustomer(customerRequestMapper.toCustomer(customerRequestDto));
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerServicePort.deleteCustomerById(id);
    }
}
