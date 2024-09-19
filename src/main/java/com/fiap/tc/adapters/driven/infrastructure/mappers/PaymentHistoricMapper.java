package com.fiap.tc.adapters.driven.infrastructure.mappers;

import com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderPaymentHistoricEntity;
import com.fiap.tc.core.domain.entities.PaymentHistoric;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentHistoricMapper extends MapperEntity<OrderPaymentHistoricEntity, PaymentHistoric> {

    @Override
    PaymentHistoric fromEntity(OrderPaymentHistoricEntity entity);

    @Override
    OrderPaymentHistoricEntity toEntity(PaymentHistoric domain);


}


