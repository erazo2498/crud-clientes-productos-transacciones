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

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class CustomerMySqlAdapter implements ICustomerPersistencePort {
    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerEntityMapper.
                toCustomer(customerRepository.findById(customerId).
                        orElseThrow(CustomerNotFoundException::new));
    }

    @Override
    public void createCustomer(Customer customer) {
        if (customerRepository.existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(
                customer.getIdentificationNumber(),
                customer.getIdentificationType())
        ) {
            throw new CustomerAlreadyExistsException();
        }
        customer.setCreatedDate(LocalDateTime.now());
        customer.setModifiedDate(LocalDateTime.now());
        customerRepository.save(customerEntityMapper.toEntity(customer));
    }

    @Override
    public void updateCustomer(Customer customer) {
        modifyDateIfPresent(customer);
        validateIfIdentificationExistInOtherCustomer(customer);
        customerRepository.save(customerEntityMapper.toEntity(customer));
    }

    @Override
    public void deleteCustomerById(Long id) {
        if(!customerRepository.existsById(id)){
            throw new CustomerNotFoundException();
        }
        customerRepository.deleteById(id);
    }

    @Override
    public boolean existCustomerById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public boolean haveCustomerAnyProductAssociated(Long customerId) {
        return customerRepository.haveCustomerAnyProductAssociated(customerId) == 1;
    }

    private void modifyDateIfPresent(Customer customer) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customer.getId());
        customerEntity.ifPresentOrElse(
                customerEntity1 -> {
                    customer.setCreatedDate(customerEntity1.getCreatedDate());
                    customer.setModifiedDate(LocalDateTime.now());
                },
                () -> {
                    throw new CustomerNotFoundException();
                }
        );
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
