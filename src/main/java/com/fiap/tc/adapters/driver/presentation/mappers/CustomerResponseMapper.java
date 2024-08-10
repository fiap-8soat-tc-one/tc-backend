package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.CustomerResponse;
import com.fiap.tc.core.domain.entities.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerResponseMapper extends MapperEntity<Customer, CustomerResponse> {

    @Override
    Customer toDomain(CustomerResponse response);

    @Override
    CustomerResponse fromDomain(Customer customer);

}