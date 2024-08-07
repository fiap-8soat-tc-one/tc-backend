package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.core.domain.model.OrderItem;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderItemEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
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


