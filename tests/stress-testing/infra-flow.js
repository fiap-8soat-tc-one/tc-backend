import { group } from "k6";
import { ShouldBeGetVerifyApplicationIsHealthy } from "../scenarios/infra/health-check.js";

export const options = {
  scenarios: {
    healthCheck: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "GetVerifyApplicationIsHealthy"
    },
 }
}

postman[Symbol.for("initial")]({
    options,
    collection: {
      BASE_URL: "http://localhost:8080"
    }
  });
  
export function GetVerifyApplicationIsHealthy() {
    group("Endpoint GET api/public/v1/health", () => { ShouldBeGetVerifyApplicationIsHealthy() });
  }