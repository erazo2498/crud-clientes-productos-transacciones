package com.quind.pruebatecnica.domain.spi;

import com.quind.pruebatecnica.domain.model.Product;

import java.util.Optional;

public interface IProductPersistencePort {
    void createProduct(Product product);
    void updateProduct(Product product);
    Optional<Product> getProduct(Long id);
    boolean existProductByAccountNumber(String accountNumber);
    long getNextConsecutiveNumberAccount(String accountType);
}
