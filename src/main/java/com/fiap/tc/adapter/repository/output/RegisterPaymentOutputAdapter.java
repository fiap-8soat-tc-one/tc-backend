package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.OrderPaymentRepository;
import com.fiap.tc.adapter.repository.OrderRepository;
import com.fiap.tc.adapter.repository.builder.OrderPaymentHistoricBuilder;
import com.fiap.tc.adapter.repository.entity.OrderPaymentEntity;
import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.adapter.repository.mapper.base.MapperConstants;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.OrderPayment;
import com.fiap.tc.core.domain.model.enums.PaymentResult;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;
import com.fiap.tc.core.port.out.payment.RegisterPaymentOutputPort;
import org.springframework.stereotype.Service;

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
    public OrderPayment saveOrUpdate(OrderPaymentRequest orderPaymentRequest) {
        var orderEntityOptional = orderRepository.findByUuid(UUID.fromString(orderPaymentRequest.getTransactionNumber()));
        if (orderEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Order with uuid %s not found!", orderPaymentRequest.getTransactionNumber()));
        }

        var orderEntity = orderEntityOptional.get();
        var orderPaymentEntityOptional = orderPaymentRepository.findByOrderUuid(orderEntity.getUuid());

        if (orderPaymentEntityOptional.isPresent()) {
            var orderPaymentEntity = orderPaymentEntityOptional.get();
            orderPaymentEntity.getAudit().setUpdatedDate(LocalDateTime.now());
            orderPaymentEntity.setResult(orderPaymentRequest.getResult());
            orderPaymentEntity.setPaymentType(orderPaymentRequest.getPaymentType());
            orderPaymentEntity.setTransactionDocument(orderPaymentRequest.getTransactionDocument());
            orderPaymentEntity.setTransactionNumber(orderPaymentRequest.getTransactionNumber());
            orderPaymentEntity.setTransactionMessage(orderPaymentRequest.getTransactionMessage());
            orderPaymentEntity.getPayment_historic()
                    .add(OrderPaymentHistoricBuilder.create(orderPaymentEntity, orderPaymentRequest.getResult()));
            setDatesResult(orderPaymentRequest, orderPaymentEntity);

            orderPaymentRepository.save(orderPaymentEntity);

            return OrderPayment.builder()
                    .id(orderPaymentEntity.getUuid())
                    .idOrder(orderPaymentEntity.getUuid())
                    .result(orderPaymentEntity.getResult())
                    .build();
        }

        var newOrderPaymentEntity = MapperConstants.ORDER_PAYMENT_MAPPER.toEntity(orderPaymentRequest);

        newOrderPaymentEntity.setUuid(UUID.randomUUID());
        newOrderPaymentEntity.setOrder(orderEntity);
        newOrderPaymentEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());
        newOrderPaymentEntity.getPayment_historic()
                .add(OrderPaymentHistoricBuilder.create(newOrderPaymentEntity, orderPaymentRequest.getResult()));
        setDatesResult(orderPaymentRequest, newOrderPaymentEntity);
        orderPaymentRepository.save(newOrderPaymentEntity);
        return OrderPayment.builder()
                .id(newOrderPaymentEntity.getUuid())
                .idOrder(orderEntity.getUuid())
                .result(newOrderPaymentEntity.getResult())
                .build();
    }

    private void setDatesResult(OrderPaymentRequest orderPaymentRequest, OrderPaymentEntity orderPaymentEntity) {
        Optional.of(orderPaymentRequest.getResult())
                .filter(result -> result.equals(PaymentResult.SUCCESS))
                .ifPresentOrElse(
                        result -> orderPaymentEntity.setResultSuccessDate(LocalDateTime.now()),
                        () -> orderPaymentEntity.setResultErrorDate(LocalDateTime.now()));
    }
}
