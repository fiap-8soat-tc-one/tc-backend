package com.fiap.tc.core.domain.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    @ApiModelProperty(
            value = "Order item name",
            example = "batata frita",
            dataType = "String"
    )
    private String name;

    @ApiModelProperty(
            value = "Order item quantity",
            example = "2",
            dataType = "int"
    )
    private Integer quantity;

    @ApiModelProperty(
            value = "Order item unit value",
            example = "10",
            dataType = "BigDecimal"
    )
    private BigDecimal unitValue;

    @ApiModelProperty(
            value = "Order item total value",
            example = "20",
            dataType = "BigDecimal"
    )
    private BigDecimal total;
}
