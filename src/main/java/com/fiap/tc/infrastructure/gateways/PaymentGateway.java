package com.fiap.tc.infrastructure.gateways;

import com.fiap.tc.application.gateways.PaymentGatewaySpec;
import com.fiap.tc.domain.entities.OrderPayment;
import com.fiap.tc.domain.enums.PaymentStatus;
import com.fiap.tc.domain.enums.PaymentType;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants;
import com.fiap.tc.infrastructure.persistence.builders.OrderPaymentHistoricBuilder;
import com.fiap.tc.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.infrastructure.persistence.entities.OrderPaymentEntity;
import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.infrastructure.persistence.repositories.OrderPaymentRepository;
import com.fiap.tc.infrastructure.persistence.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class PaymentGateway implements PaymentGatewaySpec {
    private final OrderPaymentRepository repository;
    private final OrderRepository orderRepository;

    public PaymentGateway(OrderPaymentRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderPayment load(UUID idOrder) {
        var orderPaymentOptional = repository.findByOrderUuid(idOrder);
        if (orderPaymentOptional.isEmpty()) {
            throw new NotFoundException(format("Order Payment %s not found!", idOrder));
        }
        return MapperConstants.PAYMENT_MAPPER.fromEntity(orderPaymentOptional.get());
    }

    @Override
    public OrderPayment register(String transactionNumber, String transactionMessage, String transactionDocument,
                                 PaymentStatus status, PaymentType type, BigDecimal total) {
        var orderEntityOptional = orderRepository.findByUuid(UUID.fromString(transactionNumber));

        if (orderEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Order with uuid %s not found!", transactionNumber));
        }

        var orderEntity = orderEntityOptional.get();
        var orderPaymentEntityOptional = repository.findByOrderUuid(orderEntity.getUuid());

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

        repository.save(newOrderPaymentEntity);

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

        repository.save(orderPaymentEntity);

        return MapperConstants.PAYMENT_MAPPER.fromEntity(orderPaymentEntity);
    }

    private void setDatesResult(PaymentStatus result, OrderPaymentEntity orderPaymentEntity) {
        Optional.of(result)
                .filter(res -> res.equals(PaymentStatus.APPROVED))
                .ifPresentOrElse(
                        res -> orderPaymentEntity.setResultSuccessDate(LocalDateTime.now()),
                        () -> orderPaymentEntity.setResultErrorDate(LocalDateTime.now()));
    }
}
