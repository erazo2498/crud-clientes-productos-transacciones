package com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.CustomerEntity;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerAlreadyExistsException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerNotFoundException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ICustomerEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ICustomerRepository;
import com.quind.pruebatecnica.domain.model.Customer;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class CustomerMySqlAdapter implements ICustomerPersistencePort {
    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;

    @Override
    public void createCustomer(Customer customer) {
        if (customerRepository.existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(
                customer.getIdentificationNumber(),
                customer.getIdentificationType())
        ) {
            throw new CustomerAlreadyExistsException();
        }
        customerRepository.save(customerEntityMapper.toEntity(customer));
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())
        ) {
            throw new CustomerNotFoundException();
        }
        validateIfIdentificationExistInOtherCustomer(customer);
        customerRepository.save(customerEntityMapper.toEntity(customer));
    }

    private void validateIfIdentificationExistInOtherCustomer(Customer customer) {
        Optional<CustomerEntity> customerEntity = customerRepository.
                findByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(
                        customer.getIdentificationNumber(),
                        customer.getIdentificationType()
                );

        if (customerEntity.isPresent()){
            if (!customerEntity.get().getId().equals(customer.getId())){
                throw new CustomerAlreadyExistsException();
            }
        }
    }
}
