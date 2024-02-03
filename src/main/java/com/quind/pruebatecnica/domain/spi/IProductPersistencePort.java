package com.quind.pruebatecnica.domain.spi;

import com.quind.pruebatecnica.domain.model.Product;

public interface IProductPersistencePort {
    void createProduct(Product product);

    boolean existProductByAccountNumber(String accountNumber);
}
