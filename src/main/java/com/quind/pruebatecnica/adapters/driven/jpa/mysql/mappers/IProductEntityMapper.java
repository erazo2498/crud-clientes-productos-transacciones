package com.quind.pruebatecnica.adapters.driven.jpa.mysql.mappers;

import com.quind.pruebatecnica.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.quind.pruebatecnica.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductEntityMapper {
    ProductEntity toEntity(Product product);
}
