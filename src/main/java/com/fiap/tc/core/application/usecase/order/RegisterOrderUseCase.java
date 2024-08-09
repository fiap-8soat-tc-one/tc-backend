package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.ports.in.order.RegisterOrderInputPort;
import com.fiap.tc.core.application.ports.out.order.RegisterOrderOutputPort;
import com.fiap.tc.core.application.utils.QRCodeGenerator;
import com.fiap.tc.core.domain.entities.Order;
import com.fiap.tc.core.domain.entities.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Order register(UUID idCustomer, List<OrderItem> listOfItems) {

        return registerOrderOutputPort.save(idCustomer, listOfItems);


    }
}
