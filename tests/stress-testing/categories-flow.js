import { group } from "k6";

import { ShouldBeCreateCategoryReturnCreatedCategory } from "../scenarios/categories/create-category.js";
import { ShouldBeUpdateCategoryReturnUpdatedCategory } from "../scenarios/categories/update-category.js";
import { ShouldBeGetCategoriesReturnOneOrMoreCategory } from "../scenarios/categories/get-categories.js";
import { ShouldBeGetCategoryByIdReturnsCategory } from "../scenarios/categories/get-category-by-id.js";

export const options = {
  scenarios: {
    getApiV1Categories: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
        exec: "GetCategories"
    },
    getApiV1CategoryById: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
      exec: "GetCategoryById"
    },
    postApiV1Category: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
      exec: "CreateCategory"
    },
    putApiV1Category: {
      executor: 'ramping-vus',
      startVUs: 1,
      stages: [
        { duration: '1m', target: 50 },
        { duration: '2m', target: 50 },
        { duration: '1m', target: 1 },
      ],
      gracefulRampDown: '1s',
      exec: "UpdateCategory"
    },
  }
}

postman[Symbol.for("initial")]({
  options,
  collection: {
    BASE_URL: "http://localhost:8080"
  }
});

export function GetCategories() {
  group("Endpoint GET api/private/v1/categories", () => { ShouldBeGetCategoriesReturnOneOrMoreCategory() });
}

export function GetCategoryById() {
  group("Endpoint GET api/private/v1/categories/{id}", () => { ShouldBeGetCategoryByIdReturnsCategory() });
}

export function CreateCategory() {
  group("Endpoint PUT api/private/v1/categories", () => { ShouldBeCreateCategoryReturnCreatedCategory() }); 
}

export function UpdateCategory() {
  group("Endpoint PUT api/private/v1/categories", () => { ShouldBeUpdateCategoryReturnUpdatedCategory() }); 
}

