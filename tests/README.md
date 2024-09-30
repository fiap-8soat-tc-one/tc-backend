
# Smoke Test e Stress Test com k6

No contexto de testes de desempenho, **smoke tests** e **stress tests** são dois tipos de cenários distintos, e o **Grafana k6** oferece suporte para ambos, permitindo criar scripts personalizados para cada tipo de teste.

## 1. Smoke Test Scenario com k6

O **Smoke Test** é um teste de carga leve que verifica se a aplicação básica está funcionando conforme o esperado sob uma carga mínima. O objetivo é validar a estabilidade básica da aplicação, sem submetê-la a condições extremas de uso.

### Características de Smoke Testing

- **Objetivo**: Verificar se a aplicação suporta um número mínimo de usuários e se as principais funcionalidades estão disponíveis.
- **Carga**: Um pequeno número de usuários virtuais.
- **Duração**: Curta, apenas para garantir que o sistema está operando de forma básica.
- **Falha aceitável**: Quaisquer falhas identificadas aqui indicam que a aplicação não está pronta para outros testes de desempenho.

### Exemplo de cenário de Smoke Test em k6:

```javascript
import { group } from "k6";

import { ShouldBeCreateCategoryReturnCreatedCategory } from "../scenarios/categories/create-category.js";
import { ShouldBeUpdateCategoryReturnUpdatedCategory } from "../scenarios/categories/update-category.js";
import { ShouldBeGetCategoriesReturnOneOrMoreCategory } from "../scenarios/categories/get-categories.js";
import { ShouldBeGetCategoryByIdReturnsCategory } from "../scenarios/categories/get-category-by-id.js";

export const options = {
  scenarios: {
    getApiV1Categories: {
        executor: 'shared-iterations',
        vus: 1,
        iterations: 2,
        maxDuration: '5s',
        exec: "GetCategories"
    },
    getApiV1CategoryById: {
      executor: 'shared-iterations',
      vus: 1,
      iterations: 2,
      maxDuration: '5s',
      exec: "GetCategoryById"
    },
    postApiV1Category: {
      executor: 'shared-iterations',
      vus: 1,
      iterations: 2,
      maxDuration: '5s',
      exec: "CreateCategory"
    },
    putApiV1Category: {
      executor: 'shared-iterations',
      vus: 1,
      iterations: 2,
      maxDuration: '5s',
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

```

## 2. Stress Test Scenario com k6

O **Stress Test** coloca a aplicação sob condições extremas de carga para verificar sua resiliência e o ponto de falha. Esse tipo de teste ajuda a identificar os limites da aplicação e como ela se comporta quando sobrecarregada.

### Características de Stress Testing

- **Objetivo**: Avaliar o ponto de falha e o comportamento da aplicação sob extrema carga. Esse teste mostra até onde o sistema pode ser estressado antes de falhar.
- **Carga**: Aumenta progressivamente até que o sistema não consiga mais responder de forma aceitável.
- **Duração**: Geralmente longa, até que ocorra uma falha ou degradação significativa no desempenho.
- **Falha aceitável**: Aqui, as falhas são esperadas e fazem parte do teste para entender os limites do sistema.

### Exemplo de cenário de Stress Test em k6

```javascript
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


```

## Diferenças principais

- **Objetivo**:
  - **Smoke Test**: Verifica se a aplicação está "viva" e funcional sob uma carga mínima.
  - **Stress Test**: Empurra a aplicação ao limite para descobrir até onde ela aguenta sem falhar.
  
- **Carga**:
  - **Smoke Test**: Um número muito pequeno de usuários.
  - **Stress Test**: Começa com uma carga média e aumenta progressivamente até sobrecarregar o sistema.
  
- **Duração**:
  - **Smoke Test**: Curto e focado.
  - **Stress Test**: Longo, podendo durar horas para encontrar o ponto de falha.

- **Resultado esperado**:
  - **Smoke Test**: A aplicação deve funcionar corretamente.
  - **Stress Test**: A aplicação eventualmente falha, e o objetivo é entender quando e como isso acontece.
