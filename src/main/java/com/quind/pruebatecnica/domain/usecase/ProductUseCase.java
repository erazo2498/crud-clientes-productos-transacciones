package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.CustomerNotFoundException;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.ProductAlreadyExistsException;
import com.quind.pruebatecnica.domain.api.IProductServicePort;
import com.quind.pruebatecnica.domain.enums.AccountTypeEnum;
import com.quind.pruebatecnica.domain.enums.StatusEnum;
import com.quind.pruebatecnica.domain.exceptions.DomainException;
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

    @Override
    public void activateProduct(Long id) {
        Product product = productPersistencePort.getProduct(id).orElseThrow(()->
                new DomainException("No es posible activar el producto debido a que no existe"));
        if(!StatusEnum.INACTIVE.getValue().equals(product.getStatus())){
            throw new DomainException("No es posible activar el producto porque no se encuentra inactivo");
        }
        product.setStatus(StatusEnum.ACTIVE.getValue());
        product.setModifiedDate(LocalDateTime.now());
        productPersistencePort.updateProduct(product);
    }
    
    @Override
    public void inactivateProduct(Long id) {
        Product product = productPersistencePort.getProduct(id).orElseThrow(()->
                new DomainException("No es posible inactivar el producto debido a que no existe"));
        if(!StatusEnum.ACTIVE.getValue().equals(product.getStatus())){
            throw new DomainException("No es posible inactivar el producto porque no se encuentra activo");
        }
        product.setStatus(StatusEnum.INACTIVE.getValue());
        product.setModifiedDate(LocalDateTime.now());
        productPersistencePort.updateProduct(product);
    }

    @Override
    public void cancelProduct(Long id) {
        Product product = productPersistencePort.getProduct(id).orElseThrow(()->
                new DomainException("No es posible cancelar el producto debido a que no existe"));
        if(!product.getBalance().equals(BigDecimal.ZERO)){
            throw new DomainException("No es posible cancelar el producto porque su saldo no es 0");
        }

        if(StatusEnum.CANCELLED.getValue().equals(product.getStatus())){
            throw new DomainException("No es posible cancelar el producto porque ya se encuentra cancelado");
        }
        product.setStatus(StatusEnum.CANCELLED.getValue());
        product.setModifiedDate(LocalDateTime.now());
        productPersistencePort.updateProduct(product);
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
