package com.fiap.tc.infrastructure.gateways.mappers;

import com.fiap.tc.infrastructure.gateways.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.persistence.entities.OrderPaymentHistoricEntity;
import com.fiap.tc.domain.entities.PaymentHistoric;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentHistoricMapper extends MapperEntity<OrderPaymentHistoricEntity, PaymentHistoric> {

    @Override
    PaymentHistoric fromEntity(OrderPaymentHistoricEntity entity);

    @Override
    OrderPaymentHistoricEntity toEntity(PaymentHistoric domain);


}


