package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.OrderPaymentRepository;
import com.fiap.tc.adapter.repository.OrderRepository;
import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.adapter.repository.mapper.base.MapperConstants;
import com.fiap.tc.core.domain.model.OrderGatewayPayment;
import com.fiap.tc.core.domain.model.OrderPayment;
import com.fiap.tc.core.port.out.payment.RegisterPaymentOutputPort;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
public class RegisterPaymentOutputAdapter implements RegisterPaymentOutputPort {
    private final OrderRepository orderRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    public RegisterPaymentOutputAdapter(OrderRepository orderRepository, OrderPaymentRepository orderPaymentRepository) {
        this.orderRepository = orderRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public OrderPayment saveOrUpdate(OrderGatewayPayment orderGatewayPayment, UUID idOrder) {
        var orderEntity = orderRepository.findByUuid(idOrder);

        var orderPaymentEntity = orderPaymentRepository.findByOrderUuid(idOrder);

        if (nonNull(orderPaymentEntity)) {
            orderPaymentEntity.getAudit().setUpdatedDate(LocalDateTime.now());
            orderPaymentEntity.setStatus(orderGatewayPayment.getStatus());
            orderPaymentEntity.setTransactionNumber(orderGatewayPayment.getTransactionNumber());
            orderPaymentEntity.setTransactionReturn(orderGatewayPayment.getTransactionReturn());
            Optional.of(orderGatewayPayment.getPaymentDate()).ifPresent(orderPaymentEntity::setPaymentDate);
            Optional.of(orderGatewayPayment.getPendingDate()).ifPresent(orderPaymentEntity::setPendingDate);

            orderPaymentRepository.save(orderPaymentEntity);

            return OrderPayment.builder().id(orderPaymentEntity.getUuid()).status(orderPaymentEntity.getStatus()).build();
        }

        var newOrderPaymentEntity = MapperConstants.ORDER_GATEWAY_PAYMENT_MAPPER.toEntity(orderGatewayPayment);

        newOrderPaymentEntity.setUuid(UUID.randomUUID());
        newOrderPaymentEntity.setOrder(orderEntity);
        newOrderPaymentEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());

        orderPaymentRepository.save(newOrderPaymentEntity);
        return OrderPayment.builder().id(newOrderPaymentEntity.getUuid()).status(newOrderPaymentEntity.getStatus()).build();
    }
}
