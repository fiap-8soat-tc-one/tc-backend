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


// // group("Orders", function() {
// //   postman[Request]({
// //     name: "create order",
// //     id: "52cd097f-0f1a-4633-a8ad-293419437c1b",
// //     method: "POST",
// //     address: "http://localhost:8080/api/public/v1/orders",
// //     data:
// //       '{\r\n    "id_customer": "3fa85f64-5717-4562-b3fc-2c963f66afa6",\r\n    "order_items": [\r\n        {\r\n            "id_product": "b1f859e6-07df-4b67-a1cd-74d946442207",\r\n            "quantity": 1\r\n        },\r\n        {\r\n            "id_product": "68a589ce-979f-4350-bcd6-ca049f3beb16",\r\n            "quantity": 2\r\n        },\r\n        {\r\n            "id_product": "56199d36-969b-4e1b-9515-f84ffed6a19b",\r\n            "quantity": 1\r\n        },\r\n        {\r\n            "id_product": "7b3c010c-9f03-4a56-8c85-b519a5f6b86e",\r\n            "quantity": 1\r\n        }\r\n    ]\r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     }
// //   });

// //   postman[Request]({
// //     name: "update status order",
// //     id: "10ea3246-8ad4-4ff1-adba-b2211849ddf8",
// //     method: "PUT",
// //     address: "http://localhost:8080/api/private/v1/orders/status",
// //     data:
// //       '{\r\n    "id": "038b6455-c70e-426c-afe0-02ccf5bce23b",\r\n    "status": "READY"\r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "get order by id",
// //     id: "4f33cae6-6ae2-4b09-a82b-2b2a87e76210",
// //     method: "GET",
// //     address:
// //       "http://localhost:8080/api/private/v1/orders/13f6e6b0-79b7-496f-9797-1a8a28369d3b",
// //     data:
// //       '{\r\n    "id_customer": "3fa85f64-5717-4562-b3fc-2c963f66afa6",\r\n    "order_items": [\r\n        {\r\n            "id_product": "b1f859e6-07df-4b67-a1cd-74d946442207",\r\n            "quantity": 1\r\n        },\r\n        {\r\n            "id_product": "68a589ce-979f-4350-bcd6-ca049f3beb16",\r\n            "quantity": 2\r\n        },\r\n        {\r\n            "id_product": "56199d36-969b-4e1b-9515-f84ffed6a19b",\r\n            "quantity": 1\r\n        },\r\n        {\r\n            "id_product": "7b3c010c-9f03-4a56-8c85-b519a5f6b86e",\r\n            "quantity": 1\r\n        }\r\n    ],\r\n    "order_payment_request": {\r\n        "card_cvc": "123",\r\n        "card_expire_date": "10/30",\r\n        "card_number": "4111111111111111",\r\n        "card_document": "88404071039",\r\n        "card_print_name": "Myller Lobo",\r\n        "payment_type": "CREDIT"\r\n    }\r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "list of orders",
// //     id: "c5e70944-bbea-4fe7-ad0f-8b68e0eba9f0",
// //     method: "GET",
// //     address: "http://localhost:8080/api/private/v1/orders",
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });
// // });