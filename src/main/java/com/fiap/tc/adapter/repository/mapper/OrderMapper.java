package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.OrderEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.ORDER_ITEM_MAPPER;

@Mapper
public interface OrderMapper extends MapperEntity<OrderEntity, Order> {

    @Override
    @Mapping(target = "items", source = "orderEntity", qualifiedByName = "buildItems")
    @Mapping(target = "waitTime", source = "orderEntity", qualifiedByName = "calculateWaitTime")
    @Mapping(target = "id", source = "uuid")
    Order fromEntity(OrderEntity orderEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(Order order);


    @Named("buildItems")
    default List<OrderItem> buildItems(OrderEntity orderEntity) {
        return orderEntity.getItems().stream().map(ORDER_ITEM_MAPPER::fromEntity).toList();
    }

    @Named("calculateWaitTime")
    default Long calculateWaitTime(OrderEntity orderEntity) {
        return ChronoUnit.MINUTES.between(orderEntity.getAudit().getRegisterDate(), LocalDateTime.now());
    }
}


