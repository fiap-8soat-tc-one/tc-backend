package com.fiap.tc.adapters.repository.mapper;

import com.fiap.tc.adapters.repository.entity.CustomerEntity;
import com.fiap.tc.adapters.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Customer;
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