package com.fiap.tc.adapters.driver.presentation.mappers.base;

import com.fiap.tc.adapters.driver.presentation.mappers.*;
import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final CategoryResponseMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryResponseMapper.class);
    public static final CustomerResponseMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerResponseMapper.class);
    public static final ProductImageRequestMapper PRODUCT_IMAGE_REQUEST_MAPPER = Mappers.getMapper(ProductImageRequestMapper.class);
    public static final ProductResponseMapper PRODUCT_MAPPER = Mappers.getMapper(ProductResponseMapper.class);
    public static final ProductRequestMapper PRODUCT_REQUEST_MAPPER = Mappers.getMapper(ProductRequestMapper.class);
    public static final OrderListResponseMapper ORDER_LIST_MAPPER = Mappers.getMapper(OrderListResponseMapper.class);
    public static final OrderItemRequestMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemRequestMapper.class);
    public static final OrderDtoMapper ORDER_MAPPER = Mappers.getMapper(OrderDtoMapper.class);

}
