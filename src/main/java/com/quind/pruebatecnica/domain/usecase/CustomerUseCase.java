package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.domain.api.ICustomerServicePort;
import com.quind.pruebatecnica.domain.model.Customer;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;

public class CustomerUseCase implements ICustomerServicePort {
    private final ICustomerPersistencePort customerPersistencePort;

    public CustomerUseCase(ICustomerPersistencePort customerPersistencePort) {
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public void createCustomer(Customer customer) {
        customerPersistencePort.createCustomer(customer);
    }
}
