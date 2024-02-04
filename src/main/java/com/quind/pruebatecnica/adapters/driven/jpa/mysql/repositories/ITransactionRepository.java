package com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
