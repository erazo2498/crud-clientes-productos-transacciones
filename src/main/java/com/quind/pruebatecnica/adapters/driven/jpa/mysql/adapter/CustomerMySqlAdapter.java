package com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ICustomerEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ICustomerRepository;
import com.quind.pruebatecnica.domain.model.Customer;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class CustomerMySqlAdapter implements ICustomerPersistencePort {
    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;
    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customerEntityMapper.toEntity(customer));
    }
}
