package com.fiap.tc.infrastructure.gateways.mappers;

import com.fiap.tc.infrastructure.gateways.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.persistence.entities.OrderItemEntity;
import com.fiap.tc.domain.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderItemMapper extends MapperEntity<OrderItemEntity, OrderItem> {

    @Override
    @Mapping(target = "name", source = "product.description")
    OrderItem fromEntity(OrderItemEntity orderItemEntity);

    @Override
    OrderItemEntity toEntity(OrderItem orderItem);
}


