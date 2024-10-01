import { group } from "k6";
import { ShouldBeSuccessReceiveWebHookOrderPayment } from "../scenarios/payments/webhook.js";

export const options = {
  scenarios: {
    webHook: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "ReceiveWebHookOrderPayment"
    },
  }
}

postman[Symbol.for("initial")]({
    options,
    collection: {
      BASE_URL: "http://localhost:8080"
    }
  });
  
export function ReceiveWebHookOrderPayment() {
    group("Endpoint POST api/public/v1/hook/orders/payments", () => { ShouldBeSuccessReceiveWebHookOrderPayment() });
  }