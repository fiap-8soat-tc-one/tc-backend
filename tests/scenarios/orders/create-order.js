
import "../../libs/shim/core.js";
import "../../libs/shim/expect.js"
import "../../libs/shim/jsonSchema.js"
import "../../libs/shim/urijs.js";

const Request = Symbol.for("request");

export function ShouldBeCreateOrderReturnCreatedOrder() {
      postman[Request]({
        name: "create order",
        method: "POST",
        address: "{{BASE_URL}}/api/public/v1/orders",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        data: '{"id_customer": "3fa85f64-5717-4562-b3fc-2c963f66afa6", "order_items": [{"id_product" : "b1f859e6-07df-4b67-a1cd-74d946442207", "quantity": 1 }]}',
        post() {
          pm.test('should be return status code 200 when making create order', () => pm.response.to.have.status(200))
          pm.test('should be return in less than 1s when call the create order', () => pm.expect(pm.response.responseTime).to.be.below(1000))
        }
      });
}




