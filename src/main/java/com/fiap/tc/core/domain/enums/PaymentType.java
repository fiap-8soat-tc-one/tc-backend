package com.fiap.tc.core.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType {
    DEBIT("payment with debit card"),
    CREDIT("payment with credit card"),
    PIX("payment with pix");

    private final String description;
}
