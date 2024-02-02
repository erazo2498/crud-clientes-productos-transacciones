package com.quind.pruebatecnica.adapters.driving.http.mapper;

import com.quind.pruebatecnica.adapters.driving.http.dto.request.CustomerRequestDto;
import com.quind.pruebatecnica.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICustomerRequestMapper {
    Customer toCustomer(CustomerRequestDto customerRequestDto);

}
