package com.fiap.tc.infrastructure.adapter.persistence.output;

import com.fiap.tc.infrastructure.adapter.persistence.repositories.CustomerRepository;
import com.fiap.tc.infrastructure.adapter.persistence.repositories.OrderRepository;
import com.fiap.tc.infrastructure.adapter.persistence.repositories.ProductRepository;
import com.fiap.tc.infrastructure.adapter.persistence.builder.OrderHistoricBuilder;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderItemEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.embeddable.Audit;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperConstants;
import com.fiap.tc.core.exceptions.NotFoundException;
import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderItemRequest;
import com.fiap.tc.application.port.out.order.RegisterOrderOutputPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class RegisterOrderOutputAdapter implements RegisterOrderOutputPort {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public RegisterOrderOutputAdapter(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order save(UUID customerId, List<OrderItemRequest> itemsRequest) {
        var customerEntityOptional = customerRepository.findByUuid(customerId);

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());
        orderEntity.setUuid(UUID.randomUUID());

        customerEntityOptional.ifPresent(orderEntity::setCustomer);

        orderEntity.setStatus(OrderStatus.RECEIVED);

        add_items(itemsRequest, orderEntity);
        orderEntity.getOrderHistoric().add(OrderHistoricBuilder.create(orderEntity, orderEntity.getStatus()));

        var orderEntitySaved = orderRepository.save(orderEntity);

        return MapperConstants.ORDER_MAPPER.fromEntity(orderEntitySaved);
    }

    private void add_items(List<OrderItemRequest> itemsRequest, OrderEntity orderEntity) {
        if (!isEmpty(itemsRequest)) {
            var itemsEntity = itemsRequest.stream().map(req -> buildOrderItemsEntity(req, orderEntity)).toList();
            orderEntity.setItems(itemsEntity);
            orderEntity.setTotal(itemsEntity.stream().map(OrderItemEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
    }

    private OrderItemEntity buildOrderItemsEntity(OrderItemRequest itemRequest, OrderEntity orderEntity) {

        var productEntityOptional = productRepository.findByUuid(itemRequest.getIdProduct());
        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with id %s not found!", itemRequest.getIdProduct()));
        }

        var productEntity = productEntityOptional.get();
        var orderItemEntity = new OrderItemEntity();

        orderItemEntity.setOrder(orderEntity);
        orderItemEntity.setProduct(productEntity);
        orderItemEntity.setUnitValue(productEntity.getPrice());
        orderItemEntity.setQuantity(itemRequest.getQuantity());
        orderItemEntity.setTotal(calcItemTotal(itemRequest, orderItemEntity));
        orderItemEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());

        return orderItemEntity;


    }

    private BigDecimal calcItemTotal(OrderItemRequest orderItemRequest, OrderItemEntity orderItemEntity) {
        return orderItemEntity.getUnitValue().multiply(valueOf(orderItemRequest.getQuantity()))
                .setScale(2, RoundingMode.HALF_UP);
    }

}