# Documentação das APIs

## Cadastro do Cliente

## Identificação do Cliente via CPF

## Criar, editar e remover produtos

- **Este fluxo necessita de autênticação**

## Buscar produtos por categoria

## Fake checkout 

- Nota: Apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido

## Listar os pedidos

- **Este fluxo necessita de autênticação**

## Autênticação

``` sh
  curl --location 'http://localhost:8080/oauth/token' \
--header 'Authorization: Basic YW5ndWxhcjpAbmd1bEByMA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=myller' \
--data-urlencode 'password=12345678' \
--data-urlencode 'grant_type=password'

```

## Postman Collection

[Clique aqui para ser redirecionado para collection do postman](https://github.com/fiap-8soat-tc-one/tc-backend/blob/feature/review-readme/scripts/fiap_tech_challenge.postman_collection.json)