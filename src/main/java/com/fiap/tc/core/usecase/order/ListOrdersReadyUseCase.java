package com.fiap.tc.core.usecase.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import com.fiap.tc.core.port.in.order.ListOrdersReadyPreparingInputPort;
import com.fiap.tc.core.port.out.order.ListOrdersReadyPreparingOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ListOrdersReadyUseCase implements ListOrdersReadyPreparingInputPort {

    private final ListOrdersReadyPreparingOutputPort listOrdersReadyPreparingOutputPort;

    public ListOrdersReadyUseCase(ListOrdersReadyPreparingOutputPort listOrdersOutputPort) {
        this.listOrdersReadyPreparingOutputPort = listOrdersOutputPort;
    }

    @Override
    public Page<Order> list(Pageable pageable) {
        return listOrdersReadyPreparingOutputPort.list(List.of(OrderStatus.PREPARING, OrderStatus.READY), pageable);
    }
}


