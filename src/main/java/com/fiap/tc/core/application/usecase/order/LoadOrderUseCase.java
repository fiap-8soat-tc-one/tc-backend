package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.utils.QRCodeGenerator;
import com.fiap.tc.core.application.port.in.order.LoadOrderInputPort;
import com.fiap.tc.adapters.driver.presentation.response.OrderResponse;
import com.fiap.tc.core.application.port.out.order.LoadOrderOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.fiap.tc.core.domain.constants.OrderConstants.PAYMENT_LINK_STATUS;

@Service
@Slf4j
public class LoadOrderUseCase implements LoadOrderInputPort {
    private final LoadOrderOutputPort loadOrderOutputPort;
    private final QRCodeGenerator qrCodeGenerator;

    public LoadOrderUseCase(LoadOrderOutputPort loadOrderOutputPort, QRCodeGenerator qrCodeGenerator) {
        this.loadOrderOutputPort = loadOrderOutputPort;
        this.qrCodeGenerator = qrCodeGenerator;
    }

    @Override
    public OrderResponse load(UUID uuid) {
        var order = loadOrderOutputPort.load(uuid);
        return OrderResponse.builder()
                .qrCodePaymentBase64(PAYMENT_LINK_STATUS.contains(order.getStatus())
                        ? qrCodeGenerator.generate(order.orderWithTotalAsText()) : null)
                .order(order)
                .build();
    }
}







