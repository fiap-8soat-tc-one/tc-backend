package com.fiap.tc.infrastructure.gateways;

import com.fiap.tc.application.gateways.PaymentLinkGatewaySpec;
import com.fiap.tc.domain.entities.Order;
import com.fiap.tc.infrastructure.core.utils.QRCodeGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fiap.tc.domain.constants.OrderConstants.PAYMENT_LINK_STATUS;

@Service
public class PaymentLinkGateway implements PaymentLinkGatewaySpec {
    private final QRCodeGenerator qrCodeGenerator;

    public PaymentLinkGateway(QRCodeGenerator qrCodeGenerator) {
        this.qrCodeGenerator = qrCodeGenerator;
    }

    @Override
    public Optional<String> generate(Order order) {
        if (PAYMENT_LINK_STATUS.contains(order.getStatus())) {
            return Optional.ofNullable(qrCodeGenerator.generate(order.orderWithTotalAsText()));
        }
        return Optional.empty();
    }
}
