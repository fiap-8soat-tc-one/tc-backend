package com.fiap.tc.adapters.driven.infrastructure.persistence.mapper;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.OrderPaymentEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;
import org.mapstruct.Mapper;

@Mapper
public interface OrderPaymentMapper extends MapperEntity<OrderPaymentEntity, OrderPaymentRequest> {

    @Override
    OrderPaymentRequest fromEntity(OrderPaymentEntity orderPaymentEntity);

    @Override
    OrderPaymentEntity toEntity(OrderPaymentRequest orderPaymentRequest);


}


