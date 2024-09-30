package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.dtos.PaymentHistoricDto;
import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.domain.entities.PaymentHistoric;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentHistoricResponseMapper extends MapperEntity<PaymentHistoric, PaymentHistoricDto> {

    @Override
    PaymentHistoric toDomain(PaymentHistoricDto paymentHistoricDto);

    @Override
    PaymentHistoricDto fromDomain(PaymentHistoric paymentHistoric);

}


