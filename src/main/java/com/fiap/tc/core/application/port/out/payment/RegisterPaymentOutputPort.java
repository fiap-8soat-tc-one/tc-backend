package com.fiap.tc.core.application.port.out.payment;

import com.fiap.tc.core.domain.model.OrderPayment;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;

public interface RegisterPaymentOutputPort {

    OrderPayment saveOrUpdate(OrderPaymentRequest orderPaymentRequest);
}
