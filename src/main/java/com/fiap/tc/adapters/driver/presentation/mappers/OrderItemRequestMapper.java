package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.requests.OrderItemRequest;
import com.fiap.tc.core.domain.entities.OrderItem;
import org.mapstruct.Mapper;


@Mapper
public interface OrderItemRequestMapper extends MapperEntity<OrderItem, OrderItemRequest> {

    @Override
    OrderItem toDomain(OrderItemRequest response);

    @Override
    OrderItemRequest fromDomain(OrderItem entity);
}


