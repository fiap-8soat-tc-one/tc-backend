
import "../../libs/shim/core.js";
import "../../libs/shim/expect.js"
import "../../libs/shim/jsonSchema.js"
import "../../libs/shim/urijs.js";

export function ShouldBeCreateCustomerReturnCreatedCustomer() {
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
          pm.test("should be return a valid token when call the get oauth/token",  () => {
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
        name: "create/update customer",
        id: "d08d408d-c320-475d-a0f5-26b249c68f24",
        method: "PUT",
        address: "{{BASE_URL}}/api/public/v1/customers",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        data: '{"document": 44262409805, "email": "jean.silva@gmail.com", "name": "Jean Silva"}',
        auth(config) {
          config.headers.Authorization = `Bearer ${pm.globals.get("bearer")}`;
        },
        post() {
          pm.test('should be return status code 200 when making create customer', () => pm.response.to.have.status(200))
          pm.test('should be return in less than 1s when call the create customer', () => pm.expect(pm.response.responseTime).to.be.below(1000))
          pm.test('should be return object schema valid when call the create customer', () =>  pm.response.to.have.jsonSchema(customerResponseSchema))
        }
      });
}

const Request = Symbol.for("request");

const oAuthResponseSchema = {
  "properties": {
    "access_token": {
      "type": "string"
    },
    "token_type": {
      "type": "string"
    },
    "expires_in": {
      "type": "number"
    },
    "scope": {
      "type": "string"
    },
    "profile": {
      "type": "string"
    },
    "name": {
      "type": "string"
    },
    "uuid": {
      "type": "string"
    },
    "jti": {
      "type": "string"
    },
  }
};

const customerResponseSchema = {
  "properties": {
    "id": {
      "type": "string"
    },
    "document": {
      "type": "string"
    },
    "name": {
      "type": "string"
    },
    "email": {
      "type": "string"
    }                                         
  }
};