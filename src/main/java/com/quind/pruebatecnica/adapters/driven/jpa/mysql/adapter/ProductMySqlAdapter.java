package com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter;


import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
public class ProductMySqlAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public void createProduct(Product product) {
        product.setCreatedDate(LocalDateTime.now());
        product.setModifiedDate(LocalDateTime.now());
        ProductEntity entity = productEntityMapper.toEntity(product);
        productRepository.save(entity);
    }
}
