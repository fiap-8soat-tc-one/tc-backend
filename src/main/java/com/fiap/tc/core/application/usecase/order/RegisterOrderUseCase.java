package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.utils.QRCodeGenerator;
import com.fiap.tc.core.application.ports.in.order.RegisterOrderInputPort;
import com.fiap.tc.adapters.driver.presentation.requests.OrderRequest;
import com.fiap.tc.adapters.driver.presentation.response.OrderResponse;
import com.fiap.tc.core.application.ports.out.order.RegisterOrderOutputPort;
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
