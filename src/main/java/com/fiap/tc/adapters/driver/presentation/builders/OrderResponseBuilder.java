package com.fiap.tc.adapters.driver.presentation.builders;

import com.fiap.tc.adapters.driver.presentation.dtos.OrderDto;
import com.fiap.tc.adapters.driver.presentation.response.OrderResponse;
import com.fiap.tc.core.application.utils.QRCodeGenerator;
import org.springframework.stereotype.Component;

import static com.fiap.tc.core.domain.constants.OrderConstants.PAYMENT_LINK_STATUS;

@Component
public class OrderResponseBuilder {

    private final QRCodeGenerator qrCodeGenerator;

    public OrderResponseBuilder(QRCodeGenerator qrCodeGenerator) {
        this.qrCodeGenerator = qrCodeGenerator;
    }

    public OrderResponse build(OrderDto order) {
        return OrderResponse.builder()
                .qrCodePaymentBase64(PAYMENT_LINK_STATUS.contains(order.getStatus())
                        ? qrCodeGenerator.generate(order.orderWithTotalAsText()) : null)
                .order(order)
                .build();

    }
}
