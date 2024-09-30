package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.OrderListResponse;
import com.fiap.tc.domain.entities.OrderList;
import org.mapstruct.Mapper;

@Mapper
public interface OrderListResponseMapper extends MapperEntity<OrderList, OrderListResponse> {

    @Override
    OrderList toDomain(OrderListResponse response);

    @Override
    OrderListResponse fromDomain(OrderList orderList);
}


