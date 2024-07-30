package com.fiap.tc.adapter.repository.mapper.base;

import com.fiap.tc.adapter.repository.mapper.*;
import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    public static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    public static final OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    public static final OrderItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);

    public static final OrderPaymentMapper ORDER_PAYMENT_MAPPER = Mappers.getMapper(OrderPaymentMapper.class);

    public static final OrderGatewayPaymentMapper ORDER_GATEWAY_PAYMENT_MAPPER = Mappers.getMapper(OrderGatewayPaymentMapper.class);

}
