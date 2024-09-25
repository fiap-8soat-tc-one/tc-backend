package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.OrderResponse;
import com.fiap.tc.domain.entities.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderResponseMapper extends MapperEntity<Order, OrderResponse> {

    @Override
    Order toDomain(OrderResponse orderResponse);

    @Override
    OrderResponse fromDomain(Order order);
}


