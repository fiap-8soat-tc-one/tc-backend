package com.fiap.tc.adapter.repository.mapper.base;

import com.fiap.tc.adapter.repository.mapper.CategoryMapper;
import com.fiap.tc.adapter.repository.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;

public class MapperConstants {
    public static final CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    public static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);
    
    private MapperConstants() {
    }

}
