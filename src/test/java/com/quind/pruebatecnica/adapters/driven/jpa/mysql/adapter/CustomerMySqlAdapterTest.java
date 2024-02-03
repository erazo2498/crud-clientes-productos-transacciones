package com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerAlreadyExistsException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ICustomerEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ICustomerRepository;
import com.quind.pruebatecnica.configurations.DataTest;
import com.quind.pruebatecnica.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerMySqlAdapterTest {
    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ICustomerEntityMapper customerEntityMapper;

    @InjectMocks
    private CustomerMySqlAdapter customerMySqlAdapter;

    @Test
    void throwCustomerAlreadyExistsExceptionIfExistsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase() {
        //Arrange
        when(customerRepository.existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(anyString(),anyString())).thenReturn(true);

        //Act
        Exception exception = assertThrows(CustomerAlreadyExistsException.class,
                () -> customerMySqlAdapter.createCustomer(DataTest.NEW_CUSTOMER)
        );

        //Assert
        assertEquals(CustomerAlreadyExistsException.class, exception.getClass());
        verify(customerRepository).existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(anyString(),anyString());
    }
    @Test
    void createCustomer() {
        //Arrange
        when(customerRepository.existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(anyString(),anyString())).thenReturn(false);

        //Act
        customerMySqlAdapter.createCustomer(DataTest.NEW_CUSTOMER);

        //Assert
        verify(customerRepository,times(1)).save(any());
        verify(customerRepository).save(any());
    }

    @Test
    void timeAfterCreatedDate() {
        //Arrange
        when(customerRepository.existsByIdentificationNumberIgnoreCaseAndIdentificationTypeIgnoreCase(anyString(),anyString())).thenReturn(false);
        Customer customer = DataTest.NEW_CUSTOMER;

        //Act
        customerMySqlAdapter.createCustomer(customer);

        //Assert
        assertNotNull(customer.getCreatedDate());
        assertNotNull(customer.getModifiedDate());
        verify(customerRepository,times(1)).save(any());
        verify(customerRepository).save(any());
    }
}