
import "../../libs/shim/core.js";
import "../../libs/shim/expect.js"
import "../../libs/shim/jsonSchema.js"
import "../../libs/shim/urijs.js";

const Request = Symbol.for("request");

export function ShouldBeGetProductByCategoryReturnsProduct() {
      postman[Request]({
        name: "get product by id",
        method: "GET",
        address: "{{BASE_URL}}/api/public/v1/products/categories/52ad266d-bcb5-4101-a8e1-cc45cc83afad",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        post() {
          pm.test('should be return status code 200 when making the get product by id', () => pm.response.to.have.status(200))
          pm.test('should be return in less than 1s when call the get product by id', () => pm.expect(pm.response.responseTime).to.be.below(1000))
        }
      });
}