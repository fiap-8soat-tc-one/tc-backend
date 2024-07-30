package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListOrdersReadyPreparingInputPort {
    Page<Order> list(Pageable pageable);
}
