package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.dtos.PaymentHistoricDto;
import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.entities.PaymentHistoric;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentHistoricResponseMapper extends MapperEntity<PaymentHistoric, PaymentHistoricDto> {

    @Override
    PaymentHistoric toDomain(PaymentHistoricDto paymentHistoricDto);

    @Override
    PaymentHistoricDto fromDomain(PaymentHistoric paymentHistoric);

}


