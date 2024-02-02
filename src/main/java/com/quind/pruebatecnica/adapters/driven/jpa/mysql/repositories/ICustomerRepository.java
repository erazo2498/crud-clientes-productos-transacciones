package com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
