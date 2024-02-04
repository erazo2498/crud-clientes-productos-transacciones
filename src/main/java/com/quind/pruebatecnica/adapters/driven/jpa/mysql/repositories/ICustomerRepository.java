package com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(String identificationNumber, String identificationType);

    Optional<CustomerEntity> findByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(String identificationNumber, String identificationType);

    @Query(value = """
        select exists(select * FROM prueba_tecnica_quind.product where id_customer = :customerId)
    """, nativeQuery = true)
    long haveCustomerAnyProductAssociated(Long customerId);
}
