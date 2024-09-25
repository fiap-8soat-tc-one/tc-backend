package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.CustomerResponse;
import com.fiap.tc.domain.entities.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerResponseMapper extends MapperEntity<Customer, CustomerResponse> {

    @Override
    Customer toDomain(CustomerResponse response);

    @Override
    CustomerResponse fromDomain(Customer customer);

}