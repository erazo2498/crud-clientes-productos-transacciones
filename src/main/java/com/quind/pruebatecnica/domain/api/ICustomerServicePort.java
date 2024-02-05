package com.quind.pruebatecnica.domain.api;

import com.quind.pruebatecnica.domain.model.Customer;

public interface ICustomerServicePort {
    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomerById(Long id);

    Customer getCustomerById(Long customerId);
}
