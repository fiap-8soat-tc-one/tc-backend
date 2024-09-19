package com.fiap.tc.adapters.driven.infrastructure.outputs;

import com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperConstants;
import com.fiap.tc.adapters.driven.infrastructure.persistence.builders.OrderPaymentHistoricBuilder;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderPaymentEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.OrderPaymentRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.OrderRepository;
import com.fiap.tc.core.application.usecase.ports.out.payment.LoadPaymentOutputPort;
import com.fiap.tc.core.application.usecase.ports.out.payment.RegisterPaymentOutputPort;
import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.core.domain.enums.PaymentStatus;
import com.fiap.tc.core.domain.enums.PaymentType;
import com.fiap.tc.core.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class OrderPaymentOutputAdapter implements RegisterPaymentOutputPort, LoadPaymentOutputPort {
    private final OrderRepository orderRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    public OrderPaymentOutputAdapter(OrderRepository orderRepository,
                                     OrderPaymentRepository orderPaymentRepository) {
        this.orderRepository = orderRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public OrderPayment saveOrUpdate(String transactionNumber, String transactionMessage, String transactionDocument,
                                     PaymentStatus status, PaymentType type, BigDecimal total) {
        var orderEntityOptional = orderRepository.findByUuid(UUID.fromString(transactionNumber));

        if (orderEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Order with uuid %s not found!", transactionNumber));
        }

        var orderEntity = orderEntityOptional.get();
        var orderPaymentEntityOptional = orderPaymentRepository.findByOrderUuid(orderEntity.getUuid());

        if (orderPaymentEntityOptional.isPresent()) {
            return updateOrderPayment(transactionNumber, transactionMessage, transactionDocument, status, type,
                    orderPaymentEntityOptional.get());
        }


        return newOrderPayment(transactionNumber, transactionMessage, transactionDocument, status, type, orderEntity);
    }

    private OrderPayment newOrderPayment(String transactionNumber, String transactionMessage,
                                         String transactionDocument, PaymentStatus status, PaymentType type,
                                         OrderEntity orderEntity) {
        var newOrderPaymentEntity = new OrderPaymentEntity();

        newOrderPaymentEntity.setOrder(orderEntity);
        newOrderPaymentEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());
        newOrderPaymentEntity.setStatus(status);
        newOrderPaymentEntity.setPaymentType(type);
        newOrderPaymentEntity.setTransactionDocument(transactionDocument);
        newOrderPaymentEntity.setTransactionNumber(transactionNumber);
        newOrderPaymentEntity.setTransactionMessage(transactionMessage);
        newOrderPaymentEntity.getPaymentHistoric().add(OrderPaymentHistoricBuilder.create(newOrderPaymentEntity,
                status));

        setDatesResult(status, newOrderPaymentEntity);

        orderPaymentRepository.save(newOrderPaymentEntity);

        return MapperConstants.PAYMENT_MAPPER.fromEntity(newOrderPaymentEntity);
    }

    private OrderPayment updateOrderPayment(String transactionNumber, String transactionMessage,
                                            String transactionDocument, PaymentStatus status, PaymentType type,
                                            OrderPaymentEntity orderPaymentEntity) {
        orderPaymentEntity.getAudit().setUpdatedDate(LocalDateTime.now());
        orderPaymentEntity.setStatus(status);
        orderPaymentEntity.setPaymentType(type);
        orderPaymentEntity.setTransactionDocument(transactionDocument);
        orderPaymentEntity.setTransactionNumber(transactionNumber);
        orderPaymentEntity.setTransactionMessage(transactionMessage);
        orderPaymentEntity.getPaymentHistoric().add(OrderPaymentHistoricBuilder.create(orderPaymentEntity,
                status));

        setDatesResult(status, orderPaymentEntity);

        orderPaymentRepository.save(orderPaymentEntity);

        return MapperConstants.PAYMENT_MAPPER.fromEntity(orderPaymentEntity);
    }

    private void setDatesResult(PaymentStatus result, OrderPaymentEntity orderPaymentEntity) {
        Optional.of(result)
                .filter(res -> res.equals(PaymentStatus.APPROVED))
                .ifPresentOrElse(
                        res -> orderPaymentEntity.setResultSuccessDate(LocalDateTime.now()),
                        () -> orderPaymentEntity.setResultErrorDate(LocalDateTime.now()));
    }

    @Override
    public OrderPayment load(UUID orderId) {
        var orderPaymentOptional = orderPaymentRepository.findByOrderUuid(orderId);
        if (orderPaymentOptional.isEmpty()) {
            throw new NotFoundException(format("Order Payment %s not found!", orderId));
        }
        return MapperConstants.PAYMENT_MAPPER.fromEntity(orderPaymentOptional.get());
    }
}
