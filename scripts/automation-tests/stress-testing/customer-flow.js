import { group } from "k6";
import { ShouldBeGetCustomersReturnOneOrMoreCustomer } from "../scenarios/customers/get-customers.js";
import { ShouldBeGetCustomerByDocumentReturnsCustomer } from "../scenarios/customers/get-customer-by-doc.js";
import { ShouldBeCreateCustomerReturnCreatedCustomer } from "../scenarios/customers/create-customers.js";

export const options = {
  scenarios: {
    getApiV1Customers: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
      exec: "GetCustomers"
    },
    getApiV1CustomerByDoc: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
      exec: "GetCustomerByDoc"
    },
    postApiV1Customer: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
      exec: "CreateCustomer"
    },
  }
}

postman[Symbol.for("initial")]({
  options,
  collection: {
    BASE_URL: "http://localhost:8080"
  }
});

export function GetCustomers() {
  group("Endpoint GET api/private/v1/customers", () => { ShouldBeGetCustomersReturnOneOrMoreCustomer() });
}

export function GetCustomerByDoc() {
  group("Endpoint GET api/private/v1/customers/{doc}", () => { ShouldBeGetCustomerByDocumentReturnsCustomer() });
}

export function CreateCustomer() {
  group("Endpoint PUT api/public/v1/customers", () => { ShouldBeCreateCustomerReturnCreatedCustomer() }); 
}