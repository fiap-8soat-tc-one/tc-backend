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


// // group("Products", function() {
// //   postman[Request]({
// //     name: "get product",
// //     id: "01a5925f-89bb-4bf6-a3d9-509729f8ef01",
// //     method: "GET",
// //     address:
// //       "http://localhost:8080/api/private/v1/products/7b3c010c-9f03-4a56-8c85-b519a5f6b86e",
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "list products by category",
// //     id: "50e2ec54-d66e-4d19-a79a-242dfd6df690",
// //     method: "GET",
// //     address:
// //       "http://localhost:8080/api/public/v1/products/categories/52ad266d-bcb5-4101-a8e1-cc45cc83afad",
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     }
// //   });

// //   postman[Request]({
// //     name: "delete product",
// //     id: "8a36beaf-98e7-4059-b4e3-1bb020480543",
// //     method: "DELETE",
// //     address:
// //       "http://localhost:8080/api/private/v1/products/abd81c4f-bc40-40b3-b3fa-97f7d540e24e",
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "create/update product",
// //     id: "d3bcc5c2-5773-4f84-a1b7-f85a333991b5",
// //     method: "POST",
// //     address: "http://localhost:8080/api/private/v1/products",
// //     data:
// //       '{\r\n    "id_category": "243e7152-d013-4b2c-82fb-7aafa5637c94",\r\n    "name": "milkshake de chocolate com uva",\r\n    "description": "milkshake de chocolate com uva 500ml",\r\n    "price": 16.50\r\n   \r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "update product",
// //     id: "a6b2d627-529a-4e93-b836-311d9c6f0d6e",
// //     method: "PUT",
// //     address:
// //       "http://localhost:8080/api/private/v1/products/34d70891-40f1-48f6-8e87-dbe3fbd6cee3",
// //     data:
// //       '{\r\n    "id_category": "243e7152-d013-4b2c-82fb-7aafa5637c94",\r\n    "name": "milkshake de chocolate com uva",\r\n    "description": "milkshake de chocolate com uva 500ml",\r\n    "price": 16.50\r\n   \r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "upload images",
// //     id: "08d9e4cf-f4fc-4d14-8d86-85ec93216719",
// //     method: "POST",
// //     address: "http://localhost:8080/api/private/v1/products/images",
// //     data: '',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });

// //   postman[Request]({
// //     name: "delete images",
// //     id: "4c264085-a968-4d44-aacc-41fa657964a0",
// //     method: "DELETE",
// //     address: "http://localhost:8080/api/private/v1/products/images",
// //     data:
// //       '{\r\n    "id_product": "09de201e-50bd-4933-85d6-dfa5df428684",\r\n    "images": ["ef9ce0e5-f040-4998-befe-45ee19774949"]\r\n}',
// //     headers: {
// //       Accept: "application/json",
// //       "Content-Type": "application/json"
// //     },
// //     auth(config, Var) {
// //       config.headers.Authorization = `Bearer ${pm[Var]("bearer")}`;
// //     }
// //   });
// // });