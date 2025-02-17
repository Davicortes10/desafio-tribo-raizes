﻿<h1 align="center">
  Tribo Raizes - Desafio
</h1>

Você foi contratado para desenvolver uma aplicação REST simples que gerencie informações
sobre produtos.
A aplicação deve permitir a criação, atualização, remoção e consulta de produtos. Deve
também, permitir criar pedidos para serem expedidos, movimentando o estoque do produto
no sistema.

Fui responsável por desenvolver uma aplicação REST simples para gerenciar informações sobre produtos, onde implementei um front-end para melhorar a visualização. O projeto foi hospedado em uma máquina virtual na Google Cloud Platform (GCP) e utilizei o MySQL como banco de dados.

## Requisitos Funcionais:
1. A aplicação deve permitir a criação de um novo produto com os seguintes dados:
   nome, descrição, preço e quantidade em estoque.
2. A aplicação deve permitir a atualização dos dados de um produto existente.
3. A aplicação deve permitir a remoção de um produto existente.
4. A aplicação deve permitir a consulta de todos os produtos cadastrados, bem como a
   consulta de um produto específico por ID.
5. A aplicação deve permitir criar um pedido para ser expedido, movimentando estoque
   do produto no sistema
6. Todos os endpoints devem seguir as boas práticas RESTful.

## Requisitos Técnicos:
1. Utilize o Spring Boot para criar a aplicação.
2. Utilize um banco de dados em memória (por exemplo, H2) para armazenar os dados
   dos produtos.
3. Utilize o padrão DAO (Data Access Object) para realizar operações no banco de
   dados.
4. Escreva testes unitários para os serviços de criação, atualização, remoção e consulta
   de produtos.
5. Implemente o Swagger da aplicação, expondo todas os endpoints para serem
   testados


## Tecnologias
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Hibernate](https://hibernate.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Mysql](https://www.mysql.com/products/workbench/)
- [Lombok](https://projectlombok.org/)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

## Práticas adotadas
- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- GCP

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/desafio-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints
- Criar Cliente
```
POST /clientes/cadastrar
{
  "id": 0,
  "nome": "Davi",
  "cpf": "123.456.789-10",
  "email": "davi.com",
  "telefone": "98985597367",
  "endereco": "Rua inventada, 123"
}
```

- Criar Produto
```
POST /estoque/produtos/cadastrar
{
  "id": 0,
  "nome": "Produto 1",
  "descricao": "Descrição do produto 1",
  "preco": 100.0,
  "quantidade": 10
}
````

- Criar Pedido
```
POST /estoque/pedidos/cadastrar
{
  "cliente": {
    "id": 1
  },
  [
    "itemPedido"{
        "id": 0,
        "produtoId": 1,
        "quantidade": 2
    }
  ]
}
````

- Atualizar Produto
```
PUT /estoque/produtos/atualizar
{
  "id": 1,
  "nome": "Produto 1",
  "descricao": "Descrição do produto 1",
  "preco": 100.0,
  "quantidade": 10
}
````

- Atualizar Cliente
```
PUT /clientes/atualizar
{
  "id": 1,
  "nome": "Davi",
  "cpf": "123.456.789-10",
  "email": "davi.com"
  "telefone": "98985597367",
  "endereco": "Rua inventada, 123"
}
````

- Remover Produto
```
DELETE /estoque/produtos/apagar/{id}
````

- Remover Cliente
```
DELETE /clientes/apagar/{id}
````

- Consultar Pedido por Cliente
```
GET /pedidos/listarPedidosPorCliente/{clienteId}
````
