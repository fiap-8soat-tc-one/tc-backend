package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.OrderListResponse;
import com.fiap.tc.core.domain.entities.OrderList;
import org.mapstruct.Mapper;

@Mapper
public interface OrderListResponseMapper extends MapperEntity<OrderList, OrderListResponse> {

    @Override
    OrderList toDomain(OrderListResponse response);

    @Override
    OrderListResponse fromDomain(OrderList orderList);
}


