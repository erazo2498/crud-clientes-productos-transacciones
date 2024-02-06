package com.quind.pruebatecnica.domain.usecase;

import com.quind.pruebatecnica.domain.api.ITransactionServicePort;
import com.quind.pruebatecnica.domain.enums.AccountTypeEnum;
import com.quind.pruebatecnica.domain.enums.TransactionTypeEnum;
import com.quind.pruebatecnica.domain.exceptions.DomainException;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import com.quind.pruebatecnica.domain.spi.ITransactionPersistencePort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Transactional
public class TransactionUseCase implements ITransactionServicePort {
    private final ITransactionPersistencePort transactionPersistencePort;
    private final IProductPersistencePort productPersistencePort;

    public TransactionUseCase(ITransactionPersistencePort transactionPersistencePort, IProductPersistencePort productPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        if (TransactionTypeEnum.DEPOSIT.getType().equals(transaction.getType())) {
            deposit(transaction.getOriginProductId(),transaction.getValue());
        } else if (TransactionTypeEnum.WITHDRAWAL.getType().equals(transaction.getType())) {
            withdrawal(transaction.getOriginProductId(),transaction.getValue());
        } else if (TransactionTypeEnum.TRANSFER.getType().equals(transaction.getType())) {
            transfer(transaction);
        } else {
            throw new DomainException("Tipo de transacción invalida");
        }
        transactionPersistencePort.createTransaction(transaction);
    }

    public void deposit(Long productId, BigDecimal value) {
        Product product = validateProductExist(productId);

        product.setModifiedDate(LocalDateTime.now());
        product.setBalance(product.getBalance().add(value));

        productPersistencePort.updateProduct(product);
    }

    public void withdrawal(Long productId, BigDecimal value) {
        Product product = validateProductExist(productId);

        BigDecimal valueToDecrease = value.add(getGMF(value, product));

        if (AccountTypeEnum.SAVING_ACCOUNT.getAccountTypeDescription().equals(product.getAccountType()) &&
                product.getBalance().compareTo(valueToDecrease) < 0) {
            throw new DomainException("No es posible descontar el valor debido a que no cuenta con los fondos suficientes");
        }

        product.setBalance(product.getBalance().subtract(valueToDecrease));
        product.setModifiedDate(LocalDateTime.now());

        productPersistencePort.updateProduct(product);
    }

    private static BigDecimal getGMF(BigDecimal value, Product product) {
        if (!product.getExemptGMF()) {
            return value.multiply(BigDecimal.valueOf(0.004));
        }
        return BigDecimal.ZERO;
    }

    public void transfer(Transaction transaction) {
        withdrawal(transaction.getOriginProductId(), transaction.getValue());
        deposit(transaction.getDestinationProductId(), transaction.getValue());
    }

    private Product validateProductExist(Long productId) {
        return productPersistencePort.getProduct(productId).
                orElseThrow(() -> new DomainException("No es posible continuar con la transacción debido a que un producto no existe"));
    }
}
