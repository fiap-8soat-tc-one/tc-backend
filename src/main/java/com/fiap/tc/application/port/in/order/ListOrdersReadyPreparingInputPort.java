package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.model.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListOrdersReadyPreparingInputPort {
    Page<OrderList> list(Pageable pageable);
}
