package com.fiap.tc.infrastructure.gateways.mappers;

import com.fiap.tc.infrastructure.persistence.entities.OrderHistoricEntity;
import com.fiap.tc.infrastructure.gateways.mappers.base.MapperEntity;
import com.fiap.tc.domain.entities.OrderHistoric;
import org.mapstruct.Mapper;

@Mapper
public interface OrderHistoricMapper extends MapperEntity<OrderHistoricEntity, OrderHistoric> {

    @Override
    OrderHistoric fromEntity(OrderHistoricEntity orderHistoricEntity);

    @Override
    OrderHistoricEntity toEntity(OrderHistoric orderHistoric);


}


