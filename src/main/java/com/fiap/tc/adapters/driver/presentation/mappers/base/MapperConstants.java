package com.fiap.tc.adapters.driver.presentation.mappers.base;

import com.fiap.tc.adapters.driver.presentation.mappers.*;
import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final CategoryResponseMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryResponseMapper.class);
    public static final CustomerResponseMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerResponseMapper.class);
}
