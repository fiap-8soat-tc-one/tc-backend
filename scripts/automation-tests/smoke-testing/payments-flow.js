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


// // group("Payments", function() {
// //   postman[Request]({
// //     name: "payment process",
// //     id: "a342177c-fa58-438f-b4ae-07fc6b24191f",
// //     method: "POST",
// //     address: "http://localhost:8080/api/public/v1/hook/orders/payment",
// //     data:
// //       '{\r\n    "payment_type": "CREDIT",\r\n    "result": "SUCCESS",\r\n    "total": 73.25,\r\n    "transaction_document": 65750888053,\r\n    "transaction_message": "transaction confirmed",\r\n    "transaction_number": "13f6e6b0-79b7-496f-9797-1a8a28369d3b"\r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     }
// //   });
// // });