package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.OrderEntity;
import com.fiap.tc.adapter.repository.entity.OrderHistoricEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import com.fiap.tc.core.domain.response.OrderListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fiap.tc.common.constants.OrderConstants.WAIT_TIME_STATUS;

@Mapper
public interface OrderListMapper extends MapperEntity<OrderEntity, OrderListResponse> {

    @Override
    @Mapping(target = "waitTime", source = "orderEntity", qualifiedByName = "calculateWaitTime")
    @Mapping(target = "id", source = "uuid")
    OrderListResponse fromEntity(OrderEntity orderEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderListResponse orderListResponse);

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
                    .collect(Collectors.toList());

            return preparingDates.stream().findFirst();
        }

        return Optional.empty();
    }
}


