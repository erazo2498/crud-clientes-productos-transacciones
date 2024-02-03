package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerNotFoundException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.ProductAlreadyExistsException;
import com.quind.pruebatecnica.domain.api.IProductServicePort;
import com.quind.pruebatecnica.domain.enums.AccountTypeEnum;
import com.quind.pruebatecnica.domain.enums.StatusEnum;
import com.quind.pruebatecnica.domain.exceptions.NegativeBalanceException;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final ICustomerPersistencePort customerPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort, ICustomerPersistencePort customerPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public void createProduct(Product product) {
        assignAccountType(product);
        validateIfExistProduct(product);
        validateIfExistCustomer(product);
        validateBalancePositive(product);
        product.setStatus(StatusEnum.ACTIVE.getValue());
        product.setCreatedDate(LocalDateTime.now());
        product.setModifiedDate(LocalDateTime.now());
        productPersistencePort.createProduct(product);
    }

    private void validateBalancePositive(Product product) {
        if(product.getBalance().compareTo(BigDecimal.ZERO)<0){
            throw new NegativeBalanceException();
        }
    }

    private void validateIfExistProduct(Product product) {
        if(productPersistencePort.existProductByAccountNumber(product.getAccountNumber())){
            throw new ProductAlreadyExistsException();
        }
    }

    private void validateIfExistCustomer(Product product) {
        if(!customerPersistencePort.existCustomerById(product.getCustomerId())){
            throw new CustomerNotFoundException();
        }
    }

    private void assignAccountType(Product product) {
        String initialNumber = product.getAccountNumber().substring(0,2);
        product.setAccountType(
                AccountTypeEnum.
                findByAccountTypeEnumKey(
                        initialNumber
                        ).
                getAccountTypeDescription()
        );
    }

}
