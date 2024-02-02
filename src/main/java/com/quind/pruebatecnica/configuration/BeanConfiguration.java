package com.quind.pruebatecnica.configuration;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter.CustomerMySqlAdapter;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ICustomerEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ICustomerRepository;
import com.quind.pruebatecnica.domain.api.ICustomerServicePort;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;
import com.quind.pruebatecnica.domain.usecase.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;
    @Bean
    public ICustomerServicePort customerServicePort(){
        return new CustomerUseCase(customerPersistencePort());
    }

    @Bean
    public ICustomerPersistencePort customerPersistencePort(){
        return new CustomerMySqlAdapter(customerRepository,customerEntityMapper);
    }
}
