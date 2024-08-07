package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.OrderHistoric;
import com.fiap.tc.core.domain.model.OrderItem;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperConstants;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface OrderMapper extends MapperEntity<OrderEntity, Order> {

    @Override
    @Mapping(target = "items", source = "orderEntity", qualifiedByName = "buildItems")
    @Mapping(target = "orderHistoric", source = "orderEntity", qualifiedByName = "buildOrderHistoric")
    @Mapping(target = "id", source = "uuid")
    Order fromEntity(OrderEntity orderEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(Order order);


    @Named("buildItems")
    default List<OrderItem> buildItems(OrderEntity orderEntity) {
        return orderEntity.getItems().stream().map(MapperConstants.ORDER_ITEM_MAPPER::fromEntity).toList();
    }

    @Named("buildOrderHistoric")
    default List<OrderHistoric> buildOrderHistoric(OrderEntity orderEntity) {
        return orderEntity.getOrderHistoric().stream().map(MapperConstants.ORDER_HISTORIC_MAPPER::fromEntity).toList();
    }
}


