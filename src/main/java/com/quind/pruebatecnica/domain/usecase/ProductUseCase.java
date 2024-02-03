package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.domain.api.IProductServicePort;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void createProduct(Product product) {
        productPersistencePort.createProduct(product);
    }
}
