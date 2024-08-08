package com.fiap.tc.adapters.driven.infrastructure.persistence.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderHistoricEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.model.OrderHistoric;
import org.mapstruct.Mapper;

@Mapper
public interface OrderHistoricMapper extends MapperEntity<OrderHistoricEntity, OrderHistoric> {

    @Override
    OrderHistoric fromEntity(OrderHistoricEntity orderHistoricEntity);

    @Override
    OrderHistoricEntity toEntity(OrderHistoric orderHistoric);


}


