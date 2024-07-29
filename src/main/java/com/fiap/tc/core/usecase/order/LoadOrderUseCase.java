package com.fiap.tc.core.usecase.order;

import com.fiap.tc.common.qrcode.QRCodeGenerator;
import com.fiap.tc.core.domain.response.OrderResponse;
import com.fiap.tc.core.port.in.order.LoadOrderInputPort;
import com.fiap.tc.core.port.out.order.LoadOrderOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
                .qrCodeOrderBase64(qrCodeGenerator.generate(order.orderWithTotalAsText()))
                .order(order)
                .build();
    }
}







