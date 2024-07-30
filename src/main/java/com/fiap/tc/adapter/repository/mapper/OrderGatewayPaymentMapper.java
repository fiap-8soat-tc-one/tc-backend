package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.OrderPaymentEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.OrderGatewayPayment;
import org.mapstruct.Mapper;

@Mapper
public interface OrderGatewayPaymentMapper extends MapperEntity<OrderPaymentEntity, OrderGatewayPayment> {

    @Override
    OrderGatewayPayment fromEntity(OrderPaymentEntity orderPaymentEntity);

    @Override
    OrderPaymentEntity toEntity(OrderGatewayPayment orderGatewayPayment);


}


