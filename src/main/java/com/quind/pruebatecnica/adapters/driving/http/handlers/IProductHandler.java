package com.quind.pruebatecnica.adapters.driving.http.handlers;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;

public interface IProductHandler {
    void createProduct(ProductRequestDto productRequestDto);
}
