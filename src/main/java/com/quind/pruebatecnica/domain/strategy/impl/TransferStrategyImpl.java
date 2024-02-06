package com.quind.pruebatecnica.domain.strategy.impl;

import com.quind.pruebatecnica.domain.enums.AccountTypeEnum;
import com.quind.pruebatecnica.domain.exceptions.DomainException;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import com.quind.pruebatecnica.domain.strategy.ITransactionStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransferStrategyImpl implements ITransactionStrategy {

    @Override
    public void executeStrategic(Transaction transaction, IProductPersistencePort productPersistencePort) {
        originAccountDecreasesAmount(transaction,productPersistencePort);
        destinationAccountReceivesAmount(transaction,productPersistencePort);

    }

    private void destinationAccountReceivesAmount(Transaction transaction, IProductPersistencePort productPersistencePort) {
        Product product = getProduct(productPersistencePort, transaction.getDestinationProductId());
        product.setBalance(product.getBalance().add(transaction.getValue()));
        productPersistencePort.updateProduct(product);
    }

    private void originAccountDecreasesAmount(Transaction transaction, IProductPersistencePort productPersistencePort) {
        Product productOrigin = getProduct(productPersistencePort, transaction.getOriginProductId());

        BigDecimal valueToDecrease = transaction.getValue().add(getGMF(transaction.getValue(), productOrigin));

        if (AccountTypeEnum.SAVING_ACCOUNT.getAccountTypeDescription().equals(productOrigin.getAccountType()) &&
                productOrigin.getBalance().compareTo(valueToDecrease) < 0) {
            throw new DomainException("No es posible descontar el valor debido a que no cuenta con los fondos suficientes");
        }

        productOrigin.setBalance(productOrigin.getBalance().subtract(valueToDecrease));
        productPersistencePort.updateProduct(productOrigin);
    }

    private static Product getProduct(IProductPersistencePort productPersistencePort, Long transaction) {
        return productPersistencePort.getProduct(transaction).
                orElseThrow(() -> new DomainException("No es posible continuar con la transacción debido a que un producto no existe"));
    }

    private BigDecimal getGMF(BigDecimal value, Product product) {
        return !product.getExemptGMF()? value.multiply(BigDecimal.valueOf(0.004)) : BigDecimal.ZERO;
    }
}
