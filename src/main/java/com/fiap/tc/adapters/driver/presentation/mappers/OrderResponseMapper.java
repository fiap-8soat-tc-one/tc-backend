package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.OrderResponse;
import com.fiap.tc.core.domain.entities.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderResponseMapper extends MapperEntity<Order, OrderResponse> {

    @Override
    Order toDomain(OrderResponse orderResponse);

    @Override
    OrderResponse fromDomain(Order order);
}


