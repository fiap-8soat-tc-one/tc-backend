package com.fiap.tc.core.port.out.payment;

import com.fiap.tc.core.domain.model.OrderGatewayPayment;
import com.fiap.tc.core.domain.model.OrderPayment;

import java.util.UUID;

public interface RegisterPaymentOutputPort {

    OrderPayment saveOrUpdate(OrderGatewayPayment orderGatewayPayment, UUID idOrder);
}
