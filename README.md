# Projeto pedidos

## Sobre o projeto 

Projeto de pedidos no backend. 

O projeto consiste de um domínio de pedidos de produtos. Mas para efetuar o pedido, o cliente (que faz parte do domínio) tem que ser cadastrado para utlizar os endpoint. Existe a opção do cadastro.

A ideia do projeto foi utilizar o principio de responsabilidade única que está presente no SOLID. Por isso, cada classe tem o seu encapsulamento e é pequena. Também não existem regras de negócio espalhadas, principalmente nas controllers. Para ajudar em um melhor design, os testes foram criados com o objetivo de ter a inversão de dependência e a diminuição de erros.

Esse projeto foi desenvolvido usando Spring e Java 11 com objetivo mostrar na prática como um modelo conceitual pode ser implementado
sobre o paradigma orientado a objetos, usando padrões de mercado e boas práticas.

- Leitura e entendimento do diagrama de classes
- Leitura e entendimento do diagrama de objetos
- Associações
- Um para muitos / muitos para um
- Um para um
- Muitos para muitos
- Conceito dependente
- Classe de associação
- Herança
- Enumerações
- Tipos primitivos (ItemPedidoPK)
- Entidades fracas (ElementCollection)
- Associações direcionadas

Site na nuvem usando netlify: https://app-vendas-pedido.herokuapp.com 

Imagem no Docker Hub: https://hub.docker.com/repository/docker/igorsantos100596/pedidos 

## Endpoints

- /categorias/{id} = Categoria e seus produtos
- /clientes/{id} = Cliente, seus telefones e endereços
- /pedidos/{id} = Pedido, seu cliente, seu pagamento, seus itens de pedido, seu endereço de entrega


## Modelo conceitual
![Modelo Conceitual](https://github.com/igor-lourenco/project-vendas-pedidos/blob/main/assets/images/modelo-conceitual.png)

## Diagrama de classes
![Diagrama_classes](https://github.com/igor-lourenco/project-vendas-pedidos/blob/main/assets/images/diagrama-classes.png)

## Padrão camadas
![Modelo Conceitual](https://github.com/igor-lourenco/projeto-spring-react-vendas/blob/main/frontend/src/assets/img/padrao_camadas.png)

# Tecnologias utilizadas

## Back end
- Java 11
- Spring Boot
- Spring Cloud
- JWT
- OAuth2
- JPA / Hibernate
- Maven
- MySql
- Docker

# Autor

Igor Lourenço Dos Santos

https://www.linkedin.com/in/igor-lourenco-santos


