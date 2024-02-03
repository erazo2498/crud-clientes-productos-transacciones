package com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByAccountNumberIgnoreCase(String accountNumber);
}
