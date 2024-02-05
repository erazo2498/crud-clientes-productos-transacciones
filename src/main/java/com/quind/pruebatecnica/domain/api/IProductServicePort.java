package com.quind.pruebatecnica.domain.api;

import com.quind.pruebatecnica.domain.model.Product;

public interface IProductServicePort {
    void createProduct(Product product);
    void inactivateProduct(Long id);
    void activateProduct(Long id);
    void cancelProduct(Long id);
    Product getProduct(Long productId);
}
