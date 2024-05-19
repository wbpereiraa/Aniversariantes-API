<h1 align="center">
  API geradora de cupons para aniversariantes.
</h1>

API para gerar cupons utilizando os princípios do (CRUD) e utilizando como operações principais o Create(POST) e o Read(GET).

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [PostgreSQL](https://www.postgresql.org/download/)

## Práticas adotadas

- SOLID, DRY
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/crud-0.0.1-SNAPSHOT.jar
```

A API poderá ser visualizada com Swagger em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta Insomnia (https://insomnia.rest/download):

- Consultar se o e-mail está cadastrado e gera o número do cupom, se não tiver cadastrado, o cadastro é realizado automaticamente
```
$ http://localhost:8080/email

[
  {
   "name": "Inserir nome desejado", (opcional)
   "email": "Inserir email a buscar no Banco" (obrigatório)
  }
]
```

- Consulta se o email está cadastrado e o cupom de desconto ainda está ativo
```
$ localhost:8080/email/id

[
  {
    EMAIL ATIVO - O cupom é válido ate dia XX-XX-XX.Faltam X dias para vencimento do cupom.

                                OU

    EMAIL INATIVO - O cupom não está dentro da validade de 15 dias.
  }
]
```
