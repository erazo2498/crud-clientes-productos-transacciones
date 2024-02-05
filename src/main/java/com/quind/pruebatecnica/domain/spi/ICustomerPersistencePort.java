package com.quind.pruebatecnica.domain.spi;

import com.quind.pruebatecnica.domain.model.Customer;

public interface ICustomerPersistencePort {
    Customer getCustomerById(Long customerId);
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomerById(Long customerId);
    boolean existCustomerById(Long customerId);
    boolean haveCustomerAnyProductAssociated(Long customerId);

}
