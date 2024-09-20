package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.ports.in.order.ListOrdersInputPort;
import com.fiap.tc.core.application.ports.out.order.ListOrdersOutputPort;
import com.fiap.tc.core.domain.entities.OrderList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.tc.core.domain.enums.OrderStatus.*;

@Service
@Slf4j
public class ListOrdersUseCase implements ListOrdersInputPort {

    private final ListOrdersOutputPort listOrdersOutputPort;

    public ListOrdersUseCase(ListOrdersOutputPort listOrdersOutputPort) {
        this.listOrdersOutputPort = listOrdersOutputPort;
    }

    @Override
    public Page<OrderList> list(Pageable pageable) {
        return listOrdersOutputPort.list(List.of(READY.name(), PREPARING.name(), RECEIVED.name()),
                pageable);
    }
}


