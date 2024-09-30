// import { group } from "k6";
// import { ShouldBeGetCustomersReturnOneOrMoreCustomer } from "../scenarios/customers/get-customers.js";
// import { ShouldBeGetCustomerByDocumentReturnsCustomer } from "../scenarios/customers/get-customer-by-doc.js";
// import { ShouldBeCreateCustomerReturnCreatedCustomer } from "../scenarios/customers/create-customers.js";

// export const options = {
//   scenarios: {
//     getApiV1Customers: {
//         executor: 'shared-iterations',
//         vus: 1,
//         iterations: 2,
//         maxDuration: '5s',
//         exec: "GetCustomers"
//     },
//     getApiV1CustomerByDoc: {
//       executor: 'shared-iterations',
//       vus: 1,
//       iterations: 2,
//       maxDuration: '5s',
//       exec: "GetCustomerByDoc"
//     },
//     postApiV1Customer: {
//       executor: 'shared-iterations',
//       vus: 1,
//       iterations: 2,
//       maxDuration: '5s',
//       exec: "CreateCustomer"
//     },
//   }
// }

// postman[Symbol.for("initial")]({
//   options,
//   collection: {
//     BASE_URL: "http://localhost:8080"
//   }
// });

// export function GetCustomers() {
//   group("Endpoint GET api/private/v1/customers", () => { ShouldBeGetCustomersReturnOneOrMoreCustomer() });
// }

// export function GetCustomerByDoc() {
//   group("Endpoint GET api/private/v1/customers/{doc}", () => { ShouldBeGetCustomerByDocumentReturnsCustomer() });
// }

// export function CreateCustomer() {
//   group("Endpoint PUT api/public/v1/customers", () => { ShouldBeCreateCustomerReturnCreatedCustomer() }); 
// }


// // group("Infraestructure", function() {
// //   postman[Request]({
// //     name: "health check",
// //     id: "945c9c4d-8aec-4949-af63-e49d00b0ee58",
// //     method: "GET",
// //     address: "http://localhost:8080/api/public/v1/health"
// //   });
// // });
