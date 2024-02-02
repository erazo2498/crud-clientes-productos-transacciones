package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.domain.api.ICustomerServicePort;
import com.quind.pruebatecnica.domain.exceptions.AgeNoValidException;
import com.quind.pruebatecnica.domain.model.Customer;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;

import java.time.LocalDate;
import java.time.Period;

public class CustomerUseCase implements ICustomerServicePort {
    private final ICustomerPersistencePort customerPersistencePort;

    public CustomerUseCase(ICustomerPersistencePort customerPersistencePort) {
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public void createCustomer(Customer customer) {
        validateCustomerIsOlder(customer.getBirthday());
        customerPersistencePort.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        validateCustomerIsOlder(customer.getBirthday());
        customerPersistencePort.updateCustomer(customer);
    }

    private void validateCustomerIsOlder(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthday, currentDate);
        if (age.getYears() < 18) {
            throw new AgeNoValidException();
        }
    }
}
