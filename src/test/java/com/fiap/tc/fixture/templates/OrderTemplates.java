package com.fiap.tc.fixture.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.*;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.adapters.driver.presentation.requests.OrderItemRequest;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.adapters.driver.presentation.requests.OrderRequest;
import com.fiap.tc.adapters.driver.presentation.requests.OrderStatusRequest;
import com.fiap.tc.core.domain.entities.Order;
import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.core.domain.enums.PaymentResult;
import com.fiap.tc.core.domain.enums.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderTemplates implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(OrderEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Integer.class, range(1, 100)));
                add("uuid", UUID.randomUUID());
                add("customer", one(CustomerEntity.class, "valid"));
                add("status", random(OrderStatus.RECEIVED, OrderStatus.PREPARING, OrderStatus.READY,
                        OrderStatus.FINISHED, OrderStatus.PENDING));
                add("audit", one(Audit.class, "valid"));
                add("items", has(2).of(OrderItemEntity.class, "valid"));
                add("total", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));
            }
        });

        Fixture.of(Order.class).addTemplate("valid", new Rule() {
            {
                add("id", UUID.randomUUID());
                add("orderNumber", random("1234", "5678"));
                add("status", random(OrderStatus.RECEIVED));
                add("total", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));
            }
        });

        Fixture.of(OrderItemEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Integer.class, range(1, 100)));
                add("product", one(ProductEntity.class, "valid"));
                add("quantity", random(Integer.class, range(1, 10)));
                add("unitValue", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));
                add("total", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));
                add("audit", one(Audit.class, "valid"));
            }
        });

        Fixture.of(OrderRequest.class).addTemplate("valid", new Rule() {
            {
                add("idCustomer", UUID.randomUUID());
                add("orderItems", has(2).of(OrderItemRequest.class, "valid"));
            }
        });

        Fixture.of(OrderItemRequest.class).addTemplate("valid", new Rule() {
            {
                add("idProduct", UUID.randomUUID());
                add("quantity", random(Integer.class, range(1, 10)));

            }
        });

        Fixture.of(OrderStatusRequest.class).addTemplate("valid", new Rule() {
            {
                add("id", UUID.randomUUID());
                add("status", random(OrderStatus.READY, OrderStatus.FINISHED));
            }
        });

        Fixture.of(OrderPaymentRequest.class).addTemplate("valid", new Rule() {
            {
                add("transactionNumber", "7ba2a960-2354-466f-8868-6ad713742407");
                add("transactionMessage", random("transaction successfully", "transaction error",
                        "transaction refused"));
                add("transactionDocument", random("52735617017", "03014336076", "90819176095"));
                add("result", PaymentResult.SUCCESS);
                add("paymentType", random(PaymentType.PIX, PaymentType.CREDIT, PaymentType.DEBIT));
                add("total", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));

            }
        });

        Fixture.of(OrderPaymentRequest.class).addTemplate("error", new Rule() {
            {
                add("transactionNumber", "7ba2a960-2354-466f-8868-6ad713742407");
                add("transactionMessage", random("transaction successfully", "transaction error",
                        "transaction refused"));
                add("transactionDocument", random("52735617017", "03014336076", "90819176095"));
                add("result", PaymentResult.ERROR);
                add("paymentType", random(PaymentType.PIX, PaymentType.CREDIT, PaymentType.DEBIT));
                add("total", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));

            }
        });

        Fixture.of(OrderPaymentEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", random(Integer.class, range(1, 100)));
                add("uuid", UUID.randomUUID());
                add("transactionNumber", "7ba2a960-2354-466f-8868-6ad713742407");
                add("transactionMessage", random("transaction successfully", "transaction error",
                        "transaction refused"));
                add("transactionDocument", random("52735617017", "03014336076", "90819176095"));
                add("result", random(PaymentResult.SUCCESS, PaymentResult.ERROR));
                add("paymentType", random(PaymentType.PIX, PaymentType.CREDIT, PaymentType.DEBIT));
                add("total", random(BigDecimal.valueOf(100.50), BigDecimal.valueOf(200.75)));
                add("audit", one(Audit.class, "valid"));

            }
        });


        Fixture.of(OrderPayment.class).addTemplate("valid", new Rule() {
            {
                add("id", UUID.randomUUID());
                add("idOrder", UUID.randomUUID());
                add("result", random(PaymentResult.SUCCESS, PaymentResult.ERROR));
            }
        });

    }

}
