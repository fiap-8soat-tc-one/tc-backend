package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.requests.OrderItemRequest;
import com.fiap.tc.domain.entities.OrderItem;
import org.mapstruct.Mapper;


@Mapper
public interface OrderItemRequestMapper extends MapperEntity<OrderItem, OrderItemRequest> {

    @Override
    OrderItem toDomain(OrderItemRequest request);

    @Override
    OrderItemRequest fromDomain(OrderItem orderItem);
}


