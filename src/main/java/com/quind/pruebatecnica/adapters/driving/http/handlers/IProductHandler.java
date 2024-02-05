package com.quind.pruebatecnica.adapters.driving.http.handlers;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.response.ProductResponseDto;

public interface IProductHandler {
    void createProduct(ProductRequestDto productRequestDto);
    void activateProduct(Long id);
    void inactivateProduct(Long id);
    void cancelProduct(Long id);
    ProductResponseDto getProduct(Long productId);
}
