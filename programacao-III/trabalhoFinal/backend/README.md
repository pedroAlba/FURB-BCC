### Backend

Pré requisitos para execução:

- Java 8+
- PostgreSQL 10
- Maven

Configurar a conexão com o banco de dados no arquivo ```application.properties```

```
spring.datasource.url = jdbc:postgresql://localhost:5432/dbName
spring.datasource.username = usuario
spring.datasource.password = senha
```

O hibernate cria as tabelas, mas não cria a database. Necessário criar uma database com o mesmo nome definido na string de conexão.

Para iniciar o projeto:

```
mvn spring-boot:run
```

A documentação da api é acessível em: 

```
http://localhost:8080/swagger-ui.html
```
