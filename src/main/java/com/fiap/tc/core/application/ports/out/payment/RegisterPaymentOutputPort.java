package com.fiap.tc.core.application.ports.out.payment;

import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;

public interface RegisterPaymentOutputPort {

    OrderPayment saveOrUpdate(OrderPaymentRequest orderPaymentRequest);
}
