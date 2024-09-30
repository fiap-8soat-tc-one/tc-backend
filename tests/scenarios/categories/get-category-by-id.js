
import "../../libs/shim/core.js";
import "../../libs/shim/expect.js"
import "../../libs/shim/jsonSchema.js"
import "../../libs/shim/urijs.js";

import { oAuthResponseSchema } from "../../schemas/oauth-schema.js";
const Request = Symbol.for("request");

export function ShouldBeGetCategoryByIdReturnsCategory() {
    postman[Request]({
        name: "login",
        method: "POST",
        address: "{{BASE_URL}}/oauth/token",
        data: {
          username: "myller",
          password: "12345678",
          grant_type: "password"
        },
        headers: {
          Authorization: "Basic dGNfY2xpZW50OnRlY2hfY2hhbGxlbmdlX2FwcA==",
          "Content-Type": "application/x-www-form-urlencoded"
        },
        post() {
          const token =  pm.response.json().access_token;
  
          pm.test('should be return  status code 200 when making the get oauth/token', () => pm.response.to.have.status(200))
          pm.test('should be return in less than a 1s when call the get oauth/token', () => pm.expect(pm.response.responseTime).to.be.below(1000))
          pm.test('should be return object schema valid when call the get oauth/token', () =>  pm.response.to.have.jsonSchema(oAuthResponseSchema))
          pm.test("should be return a valid token when call the get oauth/token", function () {
            const tokenParts = token.split('.');
            pm.expect(tokenParts.length).to.eql(3);
            pm.expect(tokenParts[0]).to.match(/^[A-Za-z0-9-_]+$/);
            pm.expect(tokenParts[1]).to.match(/^[A-Za-z0-9-_]+$/);
            pm.expect(tokenParts[2]).to.match(/^[A-Za-z0-9-_]+$/);
        });
          pm.globals.set("bearer",token);
        }
      });
  
      postman[Request]({
        name: "get category by id",
        method: "GET",
        address: "{{BASE_URL}}/api/private/v1/categories/f6eba314-0ea2-48cc-9a19-c5790c887c85",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        auth(config) {
          config.headers.Authorization = `Bearer ${pm.globals.get("bearer")}`;
        },
        post() {
          pm.test('should be return status code 200 when making the get categories by id', () => pm.response.to.have.status(200))
          pm.test('should be return in less than 1s when call the get categories by id', () => pm.expect(pm.response.responseTime).to.be.below(1000))
        }
      });
}