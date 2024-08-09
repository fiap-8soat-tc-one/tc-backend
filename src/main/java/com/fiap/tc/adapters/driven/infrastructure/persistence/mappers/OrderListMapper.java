package com.fiap.tc.adapters.driven.infrastructure.persistence.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderHistoricEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.entities.OrderList;
import com.fiap.tc.core.domain.enums.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.fiap.tc.core.domain.constants.OrderConstants.WAIT_TIME_STATUS;

@Mapper
public interface OrderListMapper extends MapperEntity<OrderEntity, OrderList> {

    @Override
    @Mapping(target = "waitTime", source = "orderEntity", qualifiedByName = "calculateWaitTime")
    @Mapping(target = "id", source = "uuid")
    OrderList fromEntity(OrderEntity orderEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderList orderList);

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


