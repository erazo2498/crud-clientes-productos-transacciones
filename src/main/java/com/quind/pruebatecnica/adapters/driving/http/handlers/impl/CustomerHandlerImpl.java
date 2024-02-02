package com.quind.pruebatecnica.adapters.driving.http.handlers.impl;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.CustomerRequestDto;
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
    public void createCustomer(CustomerRequestDto customerRequestDto) {
        customerServicePort.createCustomer(customerRequestMapper.toCustomer(customerRequestDto));
    }
}
