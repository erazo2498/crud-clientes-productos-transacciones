package com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(String identificationNumber, String identificationType);

    Optional<CustomerEntity> findByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(String identificationNumber, String identificationType);

}
