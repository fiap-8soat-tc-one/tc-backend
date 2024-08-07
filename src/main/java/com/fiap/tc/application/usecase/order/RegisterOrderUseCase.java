package com.fiap.tc.application.usecase.order;

import com.fiap.tc.application.port.in.order.RegisterOrderInputPort;
import com.fiap.tc.application.port.out.order.RegisterOrderOutputPort;
import com.fiap.tc.common.util.QRCodeGenerator;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderRequest;
import com.fiap.tc.infrastructure.adapter.web.responses.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterOrderUseCase implements RegisterOrderInputPort {
    private final RegisterOrderOutputPort registerOrderOutputPort;
    private final QRCodeGenerator qrCodeGenerator;

    public RegisterOrderUseCase(RegisterOrderOutputPort registerOrderOutputPort, QRCodeGenerator qrCodeGenerator) {
        this.registerOrderOutputPort = registerOrderOutputPort;
        this.qrCodeGenerator = qrCodeGenerator;
    }

    @Override
    public OrderResponse register(OrderRequest orderRequest) {
        var order = registerOrderOutputPort.save(orderRequest.getIdCustomer(), orderRequest.getOrderItems());
        return OrderResponse.builder()
                .qrCodePaymentBase64(qrCodeGenerator.generate(order.orderWithTotalAsText()))
                .order(order)
                .build();
    }
}
