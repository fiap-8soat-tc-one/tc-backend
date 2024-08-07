package com.fiap.tc.infrastructure.constants;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.List;

import static com.fiap.tc.core.domain.enums.OrderStatus.*;


public class OrderConstants {

    public static final List<OrderStatus> WAIT_TIME_STATUS = List.of(PREPARING, READY);
    public static final List<OrderStatus> PAYMENT_LINK_STATUS = List.of(RECEIVED, PENDING);
}
