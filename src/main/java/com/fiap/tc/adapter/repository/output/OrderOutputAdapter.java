package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.OrderRepository;
import com.fiap.tc.adapter.repository.builder.OrderHistoricBuilder;
import com.fiap.tc.adapter.repository.mapper.base.MapperConstants;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import com.fiap.tc.core.port.out.order.ListOrdersReadyPreparingOutputPort;
import com.fiap.tc.core.port.out.order.LoadOrderOutputPort;
import com.fiap.tc.core.port.out.order.UpdateStatusOrderOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.ORDER_MAPPER;
import static java.lang.String.format;
import static java.util.Objects.isNull;

@Service
public class OrderOutputAdapter implements UpdateStatusOrderOutputPort, LoadOrderOutputPort, ListOrdersReadyPreparingOutputPort {

    private final OrderRepository orderRepository;

    public OrderOutputAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order update(UUID idOrder, OrderStatus status) {
        var orderEntity = orderRepository.findByUuid(idOrder);
        if (isNull(orderEntity)) {
            throw new NotFoundException(format("Order id %s not found!", idOrder));
        }
        orderEntity.setStatus(status);
        orderEntity.getAudit().setUpdatedDate(LocalDateTime.now());
        orderEntity.getOrderHistoric().add(OrderHistoricBuilder.create(orderEntity, orderEntity.getStatus()));
        return MapperConstants.ORDER_MAPPER.fromEntity(orderRepository.save(orderEntity));
    }

    @Override
    public Order load(UUID uuid) {
        var orderEntity = MapperConstants.ORDER_MAPPER.fromEntity(orderRepository.findByUuid(uuid));
        if (isNull(orderEntity)) {
            throw new NotFoundException(format("Order id %s not found!", uuid));
        }
        return orderEntity;
    }

    @Override
    public Page<Order> list(List<OrderStatus> status, Pageable pageable) {
        var ordersEntity = orderRepository.findByStatusIn(status, pageable);
        return ordersEntity.map(ORDER_MAPPER::fromEntity);
    }
}
