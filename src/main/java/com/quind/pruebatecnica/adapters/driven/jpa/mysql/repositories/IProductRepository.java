package com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value =
        "SELECT COALESCE(MAX(CAST(account_number AS UNSIGNED))+1,0) numero_cuenta " +
        "FROM product " +
        "WHERE account_type =:accountType "
    , nativeQuery = true)
    long getNextConsecutiveNumberAccount(@Param("accountType") String accountType);
    boolean existsByAccountNumberIgnoreCase(String accountNumber);
}
