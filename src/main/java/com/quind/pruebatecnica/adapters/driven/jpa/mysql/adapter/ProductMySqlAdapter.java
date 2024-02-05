package com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter;


import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductMySqlAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public void createProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id).map(productEntityMapper::toModel);
    }

    @Override
    public boolean existProductByAccountNumber(String accountNumber) {
        return productRepository.existsByAccountNumberIgnoreCase(accountNumber);
    }

    @Override
    public long getNextConsecutiveNumberAccount(String accountType){
        return productRepository.getNextConsecutiveNumberAccount(accountType);
    }
}
