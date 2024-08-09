package com.fiap.tc.adapters.driven.infrastructure.outputs;

import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.OrderPaymentRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.OrderRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.builders.OrderPaymentHistoricBuilder;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderPaymentEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperConstants;
import com.fiap.tc.core.domain.enums.PaymentType;
import com.fiap.tc.core.domain.exceptions.NotFoundException;
import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.core.domain.enums.PaymentResult;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.core.application.ports.out.payment.RegisterPaymentOutputPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class RegisterPaymentOutputAdapter implements RegisterPaymentOutputPort {
    private final OrderRepository orderRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    public RegisterPaymentOutputAdapter(OrderRepository orderRepository, OrderPaymentRepository orderPaymentRepository) {
        this.orderRepository = orderRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public OrderPayment saveOrUpdate(String transactionNumber, String transactionMessage, String transactionDocument, PaymentResult result, PaymentType type, BigDecimal total) {
        var orderEntityOptional = orderRepository.findByUuid(UUID.fromString(transactionNumber));
       
        if (orderEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Order with uuid %s not found!",transactionNumber));
        }

        var orderEntity = orderEntityOptional.get();
        var orderPaymentEntityOptional = orderPaymentRepository.findByOrderUuid(orderEntity.getUuid());

        if (orderPaymentEntityOptional.isPresent()) {
            var orderPaymentEntity = orderPaymentEntityOptional.get();
            orderPaymentEntity.getAudit().setUpdatedDate(LocalDateTime.now());
            orderPaymentEntity.setResult(result);
            orderPaymentEntity.setPaymentType(type);
            orderPaymentEntity.setTransactionDocument(transactionDocument);
            orderPaymentEntity.setTransactionNumber(transactionNumber);
            orderPaymentEntity.setTransactionMessage(transactionMessage);
            orderPaymentEntity.getPayment_historic().add(OrderPaymentHistoricBuilder.create(orderPaymentEntity, result));
           
            setDatesResult(result, orderPaymentEntity);

            orderPaymentRepository.save(orderPaymentEntity);

            return OrderPayment.builder()
                    .id(orderPaymentEntity.getUuid())
                    .idOrder(orderPaymentEntity.getUuid())
                    .result(orderPaymentEntity.getResult())
                    .build();
        }

        
        var newOrderPaymentEntity = new OrderPaymentEntity();

        newOrderPaymentEntity.setUuid(UUID.randomUUID());
        newOrderPaymentEntity.setOrder(orderEntity);
        newOrderPaymentEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());
        newOrderPaymentEntity.setResult(result);
        newOrderPaymentEntity.setPaymentType(type);
        newOrderPaymentEntity.setTransactionDocument(transactionDocument);
        newOrderPaymentEntity.setTransactionNumber(transactionNumber);
        newOrderPaymentEntity.setTransactionMessage(transactionMessage);
        newOrderPaymentEntity.getPayment_historic().add(OrderPaymentHistoricBuilder.create(newOrderPaymentEntity, result));

        setDatesResult(result, newOrderPaymentEntity);
        
        orderPaymentRepository.save(newOrderPaymentEntity);
       
        return OrderPayment.builder()
                .id(newOrderPaymentEntity.getUuid())
                .idOrder(orderEntity.getUuid())
                .result(newOrderPaymentEntity.getResult())
                .build();
    }

    private void setDatesResult(PaymentResult result, OrderPaymentEntity orderPaymentEntity) {
        Optional.of(result)
                .filter(x -> x.equals(PaymentResult.SUCCESS))
                .ifPresentOrElse(
                        x -> orderPaymentEntity.setResultSuccessDate(LocalDateTime.now()),
                        () -> orderPaymentEntity.setResultErrorDate(LocalDateTime.now()));
    }
}
