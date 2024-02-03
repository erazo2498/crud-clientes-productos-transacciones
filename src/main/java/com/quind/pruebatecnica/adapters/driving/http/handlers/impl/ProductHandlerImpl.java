package com.quind.pruebatecnica.adapters.driving.http.handlers.impl;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.handlers.IProductHandler;
import com.quind.pruebatecnica.adapters.driving.http.mapper.IProductRequestMapper;
import com.quind.pruebatecnica.domain.api.IProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductHandlerImpl implements IProductHandler {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;
    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        productServicePort.createProduct(productRequestMapper.toCustomer(productRequestDto));
    }
}
