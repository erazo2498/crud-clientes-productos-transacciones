package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.exceptions.ProductAlreadyExistsException;
import com.quind.pruebatecnica.domain.api.IProductServicePort;
import com.quind.pruebatecnica.domain.enums.AccountTypeEnum;
import com.quind.pruebatecnica.domain.enums.StatusEnum;
import com.quind.pruebatecnica.domain.exceptions.InvalidValueException;
import com.quind.pruebatecnica.domain.exceptions.NoDataException;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.spi.ICustomerPersistencePort;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.quind.pruebatecnica.configuration.Constants.BALANCE_NEGATIVE_MESSAGE;
import static com.quind.pruebatecnica.configuration.Constants.CUSTOMER_NOT_FOUND_MESSAGE;

@Transactional
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
        product.setAccountNumber(generateConsecutiveAccountNumber(accountTypeEnum));
        product.setAccountType(accountTypeEnum.getAccountTypeDescription());

        validateIfExistProduct(product);
        productPersistencePort.createProduct(product);
    }

    @Override
    public void activateProduct(Long id) {
        Product product = getProductWithException(id,"No es posible activar el producto debido a que no existe");

        validateStatusDifferent(StatusEnum.INACTIVE.getValue(), product.getStatus(),
                "No es posible activar el producto porque no se encuentra inactivo");
        product.setStatus(StatusEnum.ACTIVE.getValue());
        productPersistencePort.updateProduct(product);
    }

    @Override
    public void inactivateProduct(Long id) {
        Product product = getProductWithException(id,"No es posible inactivar el producto debido a que no existe");
        validateStatusDifferent(StatusEnum.ACTIVE.getValue(), product.getStatus(),
                "No es posible inactivar el producto porque no se encuentra activo");
        product.setStatus(StatusEnum.INACTIVE.getValue());
        productPersistencePort.updateProduct(product);
    }

    @Override
    public void cancelProduct(Long id) {
        Product product = getProductWithException(id,"No es posible cancelar el producto debido a que no existe");

        if(product.getBalance().compareTo(BigDecimal.ZERO) != 0){
            throw new InvalidValueException("No es posible cancelar el producto porque su saldo no es 0");
        }
        validateStatusEquals(StatusEnum.CANCELLED.getValue(), product.getStatus(),
                "No es posible cancelar el producto porque ya se encuentra cancelado");

        product.setStatus(StatusEnum.CANCELLED.getValue());
        productPersistencePort.updateProduct(product);
    }
    @Override
    public Product getProduct(Long productId) {
        return getProductWithException(productId, "El producto con el id ingresado no existe");
    }

    private Product getProductWithException(Long productId, String message) {
        return productPersistencePort.
                getProduct(productId).orElseThrow(() -> new NoDataException(message));
    }

    private String generateConsecutiveAccountNumber(AccountTypeEnum accountTypeEnum) {
        long nextConsecutive = productPersistencePort.getNextConsecutiveNumberAccount(accountTypeEnum.getAccountTypeDescription());
        return nextConsecutive>0? String.valueOf(nextConsecutive) : accountTypeEnum.getDefaultNumber();
    }

    private void validateBalancePositive(Product product) {
        if(product.getBalance().compareTo(BigDecimal.ZERO)<0){
            throw new InvalidValueException(BALANCE_NEGATIVE_MESSAGE);
        }
    }

    private void validateStatusEquals(String status1, String status2, String message) {
        if(status1.equals(status2)){
            throw new InvalidValueException(message);
        }
    }

    private void validateStatusDifferent(String status1, String status2, String message) {
        if(!status1.equals(status2)){
            throw new InvalidValueException(message);
        }
    }

    private void validateIfExistProduct(Product product) {
        if(productPersistencePort.existProductByAccountNumber(product.getAccountNumber())){
            throw new ProductAlreadyExistsException();
        }
    }

    private void validateIfExistCustomer(Product product) {
        if(!customerPersistencePort.existCustomerById(product.getCustomerId())){
            throw new NoDataException(CUSTOMER_NOT_FOUND_MESSAGE);
        }
    }

}
