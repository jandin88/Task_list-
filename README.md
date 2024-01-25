# Projeto Tasks com Spring Boot (Backend)

Aplicação para operações CRUD em tasks.

<h2># Tecnologia usadas</h2>

<p>
  ☑️<strong> Java 17 </strong> <br>
  ☑️<strong> Spring Boot </strong> <br>
  ☑️<strong> Mongo DB </strong> <br>
  ☑️<strong> Spring Security </strong> <br>
  ☑️<strong> Gradle </strong><br>
  ☑️<strong> Swagger </strong><br>
  ☑️<strong> Postman </strong><br>
</p>

<h2># Requisitos</h2>
- Java 17, MongoDb, Postman

<h2>Executar</h2>

1. Clone o repositório: 

  ```sh
    git clone https://github.com/jandin88/Task_list---SpringBoot
  ```


2. Crie um usuário de teste para execução. Abra o Postman e faça uma requisição POST para:
 ```url
    http://localhost:8080/users/register
 ```

- Corpo da requisição JSON:
```json
    {
        "email": "teste@teste.com",
        "name": "teste",
        "password": "1234"
    }
```

3. Acesse a documentação Swagger para explorar os endpoints:
- use o email e a senha do json

   [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)





