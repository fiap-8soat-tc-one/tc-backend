# Documentação das APIs

## Cadastro do Cliente :heavy_check_mark:

## Identificação do Cliente via CPF :heavy_check_mark:

## Criar, editar e remover produtos :heavy_check_mark:

- **Este fluxo necessita de autênticação** 

## Buscar produtos por categoria :heavy_check_mark:

## Fake checkout  :heavy_check_mark:

- Nota: Apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido

## Listar os pedidos :heavy_check_mark:

- **Este fluxo necessita de autênticação**

## Autênticação :heavy_check_mark:

``` sh
  curl --location 'http://localhost:8080/oauth/token' \
--header 'Authorization: Basic YW5ndWxhcjpAbmd1bEByMA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=myller' \
--data-urlencode 'password=12345678' \
--data-urlencode 'grant_type=password'

```

## Postman Collection :exclamation:

[Clique aqui para ser redirecionado para collection do postman](https://github.com/fiap-8soat-tc-one/tc-backend/blob/feature/review-readme/scripts/fiap_tech_challenge.postman_collection.json)