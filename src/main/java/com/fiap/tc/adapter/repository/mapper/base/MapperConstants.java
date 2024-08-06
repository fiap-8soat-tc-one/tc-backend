package com.fiap.tc.adapter.repository.mapper.base;

import com.fiap.tc.adapter.repository.mapper.*;
import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    public static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    public static final OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    public static final OrderListMapper ORDER_LIST_MAPPER = Mappers.getMapper(OrderListMapper.class);

    public static final OrderItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);

    public static final OrderPaymentMapper ORDER_PAYMENT_MAPPER = Mappers.getMapper(OrderPaymentMapper.class);

    public static final OrderHistoricMapper ORDER_HISTORIC_MAPPER = Mappers.getMapper(OrderHistoricMapper.class);

    public static final ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    public static final ProductRequestMapper PRODUCT_REQUEST_MAPPER = Mappers.getMapper(ProductRequestMapper.class);

    public static final ProductImageMapper PRODUCT_IMAGE_MAPPER = Mappers.getMapper(ProductImageMapper.class);

    public static final ProductImageRequestMapper PRODUCT_IMAGE_REQUEST_MAPPER = Mappers.getMapper(
            ProductImageRequestMapper.class);


}
