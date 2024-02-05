package com.quind.pruebatecnica.adapters.driving.http.mapper;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.product.ProductRequestDto;
import com.quind.pruebatecnica.adapters.driving.http.dto.response.ProductResponseDto;
import com.quind.pruebatecnica.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {

    Product toCustomer(ProductRequestDto productRequestDto);
    ProductResponseDto toProductResponseDto(Product product);

}
