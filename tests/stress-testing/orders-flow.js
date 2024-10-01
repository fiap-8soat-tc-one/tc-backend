import { group } from "k6";

import { ShouldBeCreateOrderReturnCreatedOrder } from "../scenarios/orders/create-order.js";
import { ShouldBeUpdateOrderStatusReturnUpdatedOrder } from "../scenarios/orders/update-order-status.js";
import { ShouldBeGetOrdersReturnOneOrMoreOrder } from "../scenarios/orders/get-orders.js";
import { ShouldBeGetOrderByIdReturnsOrder } from "../scenarios/orders/get-order-by-id.js";

export const options = {
  scenarios: {
    getApiV1Orders: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "GetOrders"
    },
    getApiV1OrderById: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "GetOrderById"
    },
    postApiV1Order: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "CreateOrder"
    },
    putApiV1OrderStatus: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "UpdateOrderStatus"
    },
  }
}

postman[Symbol.for("initial")]({
  options,
  collection: {
    BASE_URL: "http://localhost:8080"
  }
});

export function GetOrders() {
  group("Endpoint GET api/public/v1/orders", () => { ShouldBeGetOrdersReturnOneOrMoreOrder() });
}

export function GetOrderById() {
  group("Endpoint GET api/private/v1/orders/{id}", () => { ShouldBeGetOrderByIdReturnsOrder() });
}

export function CreateOrder() {
  group("Endpoint PUT api/private/v1/orders", () => { ShouldBeCreateOrderReturnCreatedOrder() }); 
}

export function UpdateOrderStatus() {
  group("Endpoint PUT api/private/v1/orders/status", () => { ShouldBeUpdateOrderStatusReturnUpdatedOrder() }); 
}