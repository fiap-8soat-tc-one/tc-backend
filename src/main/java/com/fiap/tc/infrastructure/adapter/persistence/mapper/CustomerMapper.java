package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.infrastructure.adapter.persistence.entity.CustomerEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper extends MapperEntity<CustomerEntity, Customer> {

    @Override
    @Mapping(target = "id", source = "uuid")
    Customer fromEntity(CustomerEntity customerEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    CustomerEntity toEntity(Customer customer);

}