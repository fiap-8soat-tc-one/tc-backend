# Tech Challenge Backend Api

## O Desafio :triangular_flag_on_post:

Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será preparado corretamente.

Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios.

Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa.

Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades:

1. **Pedido**
   - Os clientes são apresentados a uma interface de seleção na qual podem optar por se identificarem via CPF, se cadastrarem com nome, e-mail ou não se identificar, podendo montar o combo na seguinte sequência, sendo todas elas opcionais:
        - Lanche
        - Acompanhamento
        - Bebida
        - Sobremesa

**Em cada etapa é exibido o nome, descrição e preço de cada produto.**

2. **Pagamento**
   - O sistema deverá possuir uma opção de pagamento integrada para MVP. A forma de pagamento oferecida será via QRCode do Mercado Pago.
   - Nesse MVP será realizado um `fake checkout` para o fluxo de pagamento, sem integração direta com algum o Mercado Pago.

3. **Acompanhamento**
   - Uma vez que o pedido é confirmado e pago, ele é enviado para a cozinha para ser preparado. Simultaneamente deve aparecer em um monitor para o cliente acompanhar o progresso do seu pedido com as seguintes etapas:
        - Recebido
        - Em preparação
        - Pronto
        - Finalizado

4. **Entrega**
    - Quando o pedido estiver pronto, o sistema deverá notificar o cliente que ele está pronto para retirada. Ao ser retirado, o pedido deve ser atualizado para o status finalizado.

**Além das etapas do cliente, o estabelecimento precisa de um acesso administrativo:**

1. **Gerenciar clientes**
   - Com a identificação dos clientes o estabelecimento pode trabalhar em campanhas promocionais.

2. **Gerenciar produtos e categorias**
   - Os produtos dispostos para escolha do cliente serão gerenciados pelo estabelecimento, definindo nome, categoria, preço, descrição e imagens. Para esse sistema teremos categorias fixas:
        - Lanche
        - Acompanhamento
        - Bebida
        - Sobremesa

3. **Acompanhamento de pedidos**
   - Deve ser possível acompanhar os pedidos em andamento e tempo de espera de cada pedido.

As informações dispostas no sistema de pedidos precisarão ser gerenciadas pelo estabelecimento através de um painel administrativo.

## Time :construction_worker:

- Myller Lobo
- Jean Carlos
- Caio Isikawa
- Vanderly
- Thiago

## Pré-Requisitos :exclamation:

- Maven 3
- Java 17 (Open JDK 17)
- Postgres 15
- Docker Desktop
- Intellij IDEA
- DBeaver SQL Client
- Postman

---

## Configuração de ambiente de desenvolvimento local  :heavy_check_mark:

[Clique aqui para ser redirecionado para wiki de configuração do ambiente de desenvolvimento local](https://github.com/fiap-8soat-tc-one/tc-backend/blob/feature/review-readme/docs/config/README.md)

## Configuração do ambiente Docker/Docker Compose :heavy_check_mark:

[Clique aqui para ser redirecionado para wiki de configuração do ambiente Docker](https://github.com/fiap-8soat-tc-one/tc-backend/blob/feature/review-readme/docs/docker/README.md)

## Manual/Documentação de funcionalidade (Swagger/Open API) :heavy_check_mark:

[Clique aqui para ser redirecionado para documentação das funcionalidade](https://github.com/fiap-8soat-tc-one/tc-backend/blob/feature/review-readme/docs/api/README.md)

## Domain Storytelling :heavy_check_mark:

[Clique aqui para ser redirecionado para documentação do domain storytelling no miro](https://miro.com/app/board/uXjVK1Bf4Q4=/)

## Event Storming :heavy_check_mark:
 
[Clique aqui para ser redirecionado para documentação do event storming no miro](https://miro.com/app/board/uXjVK1Bf4Q4=/)

## Domain Mapping :heavy_check_mark:

![image](https://github.com/fiap-8soat-tc-one/tc-backend/blob/feature/review-readme/assets/domain-mapping.drawio.png)