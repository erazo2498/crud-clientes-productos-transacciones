package com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.CustomerEntity;
import com.quind.pruebatecnica.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICustomerEntityMapper {
    CustomerEntity toEntity(Customer customer);
    Customer toCustomer(CustomerEntity customerEntity);

}
