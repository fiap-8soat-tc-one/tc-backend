package com.fiap.tc.adapters.driven.infrastructure.outputs;

import com.fiap.tc.adapters.driven.infrastructure.utils.QRCodeGenerator;
import com.fiap.tc.core.application.ports.out.payment.PaymentLinkOutputPort;
import com.fiap.tc.core.domain.entities.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fiap.tc.core.domain.constants.OrderConstants.PAYMENT_LINK_STATUS;

@Service
public class PaymentLinkOutputAdapter implements PaymentLinkOutputPort {

    private final QRCodeGenerator qrCodeGenerator;

    public PaymentLinkOutputAdapter(QRCodeGenerator qrCodeGenerator) {
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
