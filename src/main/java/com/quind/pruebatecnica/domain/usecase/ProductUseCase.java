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
        AccountTypeEnum accountTypeEnum = AccountTypeEnum.findByAccountTypeEnumKey(product.getAccountType());
        validateIfExistCustomer(product);
        validateBalancePositive(product);

        product.setStatus(StatusEnum.ACTIVE.getValue());
        product.setCreatedDate(LocalDateTime.now());
        product.setModifiedDate(LocalDateTime.now());
        product.setAccountNumber(generateConsecutiveAccountNumber(accountTypeEnum));
        product.setAccountType(accountTypeEnum.getAccountTypeDescription());

        validateIfExistProduct(product);
        productPersistencePort.createProduct(product);
    }

    private String generateConsecutiveAccountNumber(AccountTypeEnum accountTypeEnum) {
        long nextConsecutive = productPersistencePort.getNextConsecutiveNumberAccount(accountTypeEnum.getAccountTypeDescription());
        if(nextConsecutive>0){
            return String.valueOf(nextConsecutive);
        }
        return accountTypeEnum.getDefaultNumber();
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

}
