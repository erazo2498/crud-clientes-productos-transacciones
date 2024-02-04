package com.quind.pruebatecnica.domain.spi;

import com.quind.pruebatecnica.domain.model.Customer;

public interface ICustomerPersistencePort {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomerById(Long id);
    boolean existCustomerById(Long id);
}
