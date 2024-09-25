package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.dtos.PaymentHistoricDto;
import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.OrderPaymentResponse;
import com.fiap.tc.domain.entities.OrderPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static com.fiap.tc.infrastructure.presentation.mappers.base.MapperConstants.PAYMENT_HISTORIC_RESPONSE_MAPPER;

@Mapper
public interface PaymentResponseMapper extends MapperEntity<OrderPayment, OrderPaymentResponse> {

    @Override
    OrderPayment toDomain(OrderPaymentResponse orderPaymentResponse);

    @Override
    @Mapping(target = "paymentHistoric", source = "domain", qualifiedByName = "buildPaymentHistoric")
    OrderPaymentResponse fromDomain(OrderPayment domain);

    @Named("buildPaymentHistoric")
    default List<PaymentHistoricDto> buildPaymentHistoric(OrderPayment domain) {
        return domain.getPaymentHistoric().stream().map(PAYMENT_HISTORIC_RESPONSE_MAPPER::fromDomain).toList();
    }
}


