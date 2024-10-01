
import "../../libs/shim/core.js";
import "../../libs/shim/expect.js"
import "../../libs/shim/jsonSchema.js"
import "../../libs/shim/urijs.js";

const Request = Symbol.for("request");

export function ShouldBeGetVerifyApplicationIsHealthy() {
      postman[Request]({
        name: "health check",
        method: "GET",
        address: "{{BASE_URL}}/api/public/v1/health",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        post() {
          pm.test('should be return status code 200 when making the get customers', () => pm.response.to.have.status(200))
          pm.test('should be return in less than 1s when call the get customers', () => pm.expect(pm.response.responseTime).to.be.below(1000))
        }
      });
}

