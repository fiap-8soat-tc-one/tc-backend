package com.fiap.tc.core.application.usecase.payment;

import com.fiap.tc.core.application.ports.in.payment.LoadPaymentInputPort;
import com.fiap.tc.core.application.ports.out.payment.LoadPaymentOutputPort;
import com.fiap.tc.core.domain.entities.OrderPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class LoadPaymentUseCase implements LoadPaymentInputPort {

    private final LoadPaymentOutputPort loadPaymentOutputPort;

    public LoadPaymentUseCase(LoadPaymentOutputPort loadPaymentOutputPort) {
        this.loadPaymentOutputPort = loadPaymentOutputPort;
    }


    @Override
    public OrderPayment load(UUID orderId) {
        return loadPaymentOutputPort.load(orderId);
    }
}


