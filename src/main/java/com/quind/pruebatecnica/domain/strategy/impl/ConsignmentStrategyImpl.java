package com.quind.pruebatecnica.domain.strategy.impl;

import com.quind.pruebatecnica.domain.exceptions.DomainException;
import com.quind.pruebatecnica.domain.model.Product;
import com.quind.pruebatecnica.domain.model.Transaction;
import com.quind.pruebatecnica.domain.spi.IProductPersistencePort;
import com.quind.pruebatecnica.domain.strategy.ITransactionStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ConsignmentStrategyImpl implements ITransactionStrategy {

    @Override
    public void executeStrategic(Transaction transaction, IProductPersistencePort productPersistencePort) {
        consignment(transaction, productPersistencePort);
    }

    private void consignment(Transaction transaction, IProductPersistencePort productPersistencePort) {
        Product product = productPersistencePort.getProduct(transaction.getOriginProductId()).
                orElseThrow(() -> new DomainException("No es posible continuar con la transacción debido a que un producto no existe"));
        product.setBalance(product.getBalance().add(transaction.getValue()));
        productPersistencePort.updateProduct(product);
    }


}
