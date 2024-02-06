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
public class WithdrawalStrategyImpl implements ITransactionStrategy {

    @Override
    public void executeStrategic(Transaction transaction, IProductPersistencePort productPersistencePort) {
        withdrawal(transaction, productPersistencePort);
    }

    private void withdrawal(Transaction transaction, IProductPersistencePort productPersistencePort) {
        Product product = productPersistencePort.getProduct(transaction.getOriginProductId()).
                orElseThrow(() -> new DomainException("No es posible continuar con la transacción debido a que un producto no existe"));

        BigDecimal valueToDecrease = transaction.getValue().add(getGMF(transaction.getValue(), product));

        if (AccountTypeEnum.SAVING_ACCOUNT.getAccountTypeDescription().equals(product.getAccountType()) &&
                product.getBalance().compareTo(valueToDecrease) < 0) {
            throw new DomainException("No es posible descontar el valor debido a que no cuenta con los fondos suficientes");
        }

        product.setBalance(product.getBalance().subtract(valueToDecrease));
        productPersistencePort.updateProduct(product);
    }

    private BigDecimal getGMF(BigDecimal value, Product product) {
        return !product.getExemptGMF()? value.multiply(BigDecimal.valueOf(0.004)) : BigDecimal.ZERO;
    }

}
