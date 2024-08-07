package com.fiap.tc.application.usecase.order;

import com.fiap.tc.application.port.in.order.ListOrdersReadyPreparingInputPort;
import com.fiap.tc.application.port.out.order.ListOrdersReadyPreparingOutputPort;
import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.core.domain.model.OrderList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ListOrdersReadyPreparingUseCase implements ListOrdersReadyPreparingInputPort {

    private final ListOrdersReadyPreparingOutputPort listOrdersReadyPreparingOutputPort;

    public ListOrdersReadyPreparingUseCase(ListOrdersReadyPreparingOutputPort listOrdersOutputPort) {
        this.listOrdersReadyPreparingOutputPort = listOrdersOutputPort;
    }

    @Override
    public Page<OrderList> list(Pageable pageable) {
        return listOrdersReadyPreparingOutputPort.list(List.of(OrderStatus.PREPARING, OrderStatus.READY), pageable);
    }
}


