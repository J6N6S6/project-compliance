# Project Compliance

Este projeto é uma aplicação Spring Boot que gerencia funcionários e departamentos, com operações CRUD (Create, Read, Update, Delete) e validações de dados. Ele utiliza um banco de dados PostgreSQL para persistência e Docker para containerização.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para operações de persistência com o banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados.
- **Docker**: Para containerização da aplicação e do banco de dados.
- **Flyway**: Para gerenciamento de migrações do banco de dados.
- **JUnit 5 e Mockito**: Para testes unitários e de integração.
- **Jakarta Persistence API (JPA)**: Para mapeamento objeto-relacional (ORM).

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **`src/main/java/com/jonas/project_compliance`**:
    - **`controller`**: Contém os controladores REST.
    - **`service`**: Lógica de negócio e serviços.
    - **`repository`**: Interfaces de repositório para acesso ao banco de dados.
    - **`model`**: Entidades JPA (Employee, Department, EmployeeDepartment).
    - **`DTO`**: Objetos de transferência de dados (DTOs).
    - **`mapper`**: Mapeamento entre entidades e DTOs.
    - **`validation`**: Lógica de validação de dados.

- **`src/test/java/com/jonas/project_compliance`**:
    - **`service`**: Testes unitários e de integração para os serviços.
    - **`repository`**: Testes de integração para os repositórios.

- **`src/main/resources`**:
    - **`application.properties`**: Configurações da aplicação.
    - **`db/migration`**: Scripts de migração do Flyway.

- **`docker-compose.yml`**: Configuração do Docker para subir a aplicação e o banco de dados PostgreSQL.

## Como Executar o Projeto

### Pré-requisitos

- Docker e Docker Compose instalados.
- Java 17 ou superior.

### Passos para Execução

1. Clone o repositório:
```
   git clone https://github.com/J6N6S6/project-compliance.git
   cd project-compliance
```
Suba os containers com Docker Compose:

```docker-compose up -d --build```

Acesse a aplicação:

A API estará disponível em http://localhost:8080.

Para parar os containers:

```docker-compose down
Endpoints da API
Funcionários (/employees)
POST /employees: Cria um novo funcionário.
```

Exemplo de corpo da requisição:

```json
{
"name": "John Doe",
"address": "123 Main St",
"neighborhood": "Downtown",
"zipCode": "12345",
"phoneNumber": "555-1234",
"salary": 5000.00,
"contractDate": "2023-10-01T00:00:00",
"function": "Developer"
}
```

GET /employees/{id}: Retorna um funcionário pelo ID.

GET /employees?name={name}: Retorna funcionários pelo nome.

PUT /employees/{id}: Atualiza um funcionário existente.

DELETE /employees/{id}: Remove um funcionário.

Departamentos (/departments)
POST /departments: Cria um novo departamento.

Exemplo de corpo da requisição:

```json
{
"departmentName": "Engineering",
"employeesNumber": 10
}
```
GET /departments/{id}: Retorna um departamento pelo ID.

GET /departments?name={name}: Retorna departamentos pelo nome.

PUT /departments/{id}: Atualiza um departamento existente.

DELETE /departments/{id}: Remove um departamento.

Testes
Testes Unitários
Os testes unitários estão localizados em src/test/java/com/jonas/project_compliance/service. Eles cobrem os métodos do EmployeeService e utilizam Mockito para simular dependências.

Para executar os testes, use o comando:

```
  mvn test
```
Migrações do Banco de Dados
O projeto utiliza Flyway para gerenciar migrações do banco de dados. Os scripts SQL estão localizados em src/main/resources/db/migration.

Contribuição
Faça um fork do repositório.

Crie uma branch para sua feature (git checkout -b feature/nova-feature).

Commit suas mudanças (git commit -m 'Adiciona nova feature').

Push para a branch (git push origin feature/nova-feature).

Abra um Pull Request.

Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.