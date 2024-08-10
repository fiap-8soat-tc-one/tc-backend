package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.dtos.OrderDto;
import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.entities.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDtoMapper extends MapperEntity<Order, OrderDto> {

    @Override
    Order toDomain(OrderDto orderDto);

    @Override
    OrderDto fromDomain(Order order);
}


