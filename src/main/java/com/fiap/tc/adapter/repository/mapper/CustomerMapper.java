package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.CustomerEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper extends MapperEntity<CustomerEntity, Customer> {

    @Override
    Customer fromEntity(CustomerEntity customerEntity);

    @Override
    CustomerEntity toEntity(Customer customer);

}