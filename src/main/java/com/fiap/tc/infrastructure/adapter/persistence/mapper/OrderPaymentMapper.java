package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderPaymentEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderPaymentRequest;
import org.mapstruct.Mapper;

@Mapper
public interface OrderPaymentMapper extends MapperEntity<OrderPaymentEntity, OrderPaymentRequest> {

    @Override
    OrderPaymentRequest fromEntity(OrderPaymentEntity orderPaymentEntity);

    @Override
    OrderPaymentEntity toEntity(OrderPaymentRequest orderPaymentRequest);


}


