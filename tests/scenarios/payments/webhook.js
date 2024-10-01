
import "../../libs/shim/core.js";
import "../../libs/shim/expect.js"
import "../../libs/shim/jsonSchema.js"
import "../../libs/shim/urijs.js";

const Request = Symbol.for("request");

export function ShouldBeSuccessReceiveWebHookOrderPayment() {
      postman[Request]({
        name: "web-hook order/payment",
        method: "POST",
        address: "{{BASE_URL}}/api/public/v1/hook/orders/payments",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        data: '{"payment_type": "CREDIT", "result": "SUCCESS", "status": "APPROVED", "total": 73.25, "transaction_document": 65750888053, "transaction_message": "transaction confirmed", "transaction_number": "f6af5cc6-1637-47fd-810e-3df2d2a2b878" }',
        post() {
          pm.test('should be return in less than 1s when call web hook', () => pm.expect(pm.response.responseTime).to.be.below(1000))
        }
      });
}

