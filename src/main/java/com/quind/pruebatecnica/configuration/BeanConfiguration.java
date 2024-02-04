package com.quind.pruebatecnica.configuration;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter.CustomerMySqlAdapter;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter.ProductMySqlAdapter;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.adapter.TransactionMySqlAdapter;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ICustomerEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.IProductEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers.ITransactionEntityMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ICustomerRepository;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.IProductRepository;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ITransactionRepository;
import com.quind.pruebatecnica.domain.api.ICustomerServicePort;
import com.quind.pruebatecnica.domain.api.IProductServicePort;
import com.quind.pruebatecnica.domain.api.ITransactionServicePort;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import com.quind.pruebatecnica.domain.spi.ITransactionPersistencePort;
import com.quind.pruebatecnica.domain.usecase.CustomerUseCase;
import com.quind.pruebatecnica.domain.usecase.ProductUseCase;
import com.quind.pruebatecnica.domain.usecase.TransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICustomerRepository customerRepository;
    private final ICustomerEntityMapper customerEntityMapper;

    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    private final ITransactionRepository transactionRepository;
    private final ITransactionEntityMapper transactionEntityMapper;
    @Bean
    public ICustomerServicePort customerServicePort(){
        return new CustomerUseCase(customerPersistencePort());
    }

    @Bean
    public ICustomerPersistencePort customerPersistencePort(){
        return new CustomerMySqlAdapter(customerRepository,customerEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort(), customerPersistencePort());
    }

    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductMySqlAdapter(productRepository,productEntityMapper);
    }

    @Bean
    public ITransactionServicePort transactionServicePort(){
        return new TransactionUseCase(transactionPersistencePort(), productPersistencePort());
    }

    @Bean
    public ITransactionPersistencePort transactionPersistencePort(){
        return new TransactionMySqlAdapter(transactionRepository,transactionEntityMapper);
    }
}
