package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.core.domain.model.OrderList;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderHistoricEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.fiap.tc.common.constants.OrderConstants.WAIT_TIME_STATUS;

@Mapper
public interface OrderListMapper extends MapperEntity<OrderEntity, OrderList> {

    @Override
    @Mapping(target = "waitTime", source = "orderEntity", qualifiedByName = "calculateWaitTime")
    @Mapping(target = "id", source = "uuid")
    OrderList fromEntity(OrderEntity orderEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderList orderListResponse);

    @Named("calculateWaitTime")
    default Long calculateWaitTime(OrderEntity orderEntity) {
        var preparingDate = getPreparingRegisterDate(orderEntity);
        if (preparingDate.isPresent()) {
            return ChronoUnit.MINUTES.between(preparingDate.get(), LocalDateTime.now());
        }
        return null;
    }

    default Optional<LocalDateTime> getPreparingRegisterDate(OrderEntity orderEntity) {

        if (WAIT_TIME_STATUS.contains(orderEntity.getStatus())) {
            List<LocalDateTime> preparingDates = orderEntity.getOrderHistoric().stream()
                    .filter(historic -> historic.getStatus().equals(OrderStatus.PREPARING))
                    .map(OrderHistoricEntity::getRegisterDate)
                    .toList();

            return preparingDates.stream().findFirst();
        }

        return Optional.empty();
    }
}


