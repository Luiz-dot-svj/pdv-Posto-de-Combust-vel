# PDV Posto de Combustível

API RESTful para um sistema de Ponto de Venda (PDV) para postos de combustível. Este projeto gerencia entidades como acessos, bombas, produtos, estoque e preços.

## Tecnologias Utilizadas

*   **Java 17**: Versão da linguagem Java utilizada no projeto.
*   **Spring Boot 3**: Framework principal para a construção da aplicação.
*   **Spring Data JPA**: Para persistência de dados e comunicação com o banco de dados.
*   **Hibernate**: Implementação do JPA para mapeamento objeto-relacional.
*   **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados.
*   **Maven**: Ferramenta para gerenciamento de dependências e build do projeto.
*   **SpringDoc OpenAPI (Swagger)**: Para documentação interativa da API.

## Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em seu ambiente:

*   [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
*   [Apache Maven](https://maven.apache.org/download.cgi).
*   Um cliente [PostgreSQL](https://www.postgresql.org/download/), como o `psql` ou pgAdmin.
*   Uma IDE de sua preferência (ex: IntelliJ IDEA, VS Code).

## Configuração do Ambiente

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-seu-repositorio>
    cd pdv-posto-de-combustivel
    ```

2.  **Crie o Banco de Dados:**
    Conecte-se ao seu servidor PostgreSQL e crie o banco de dados que será utilizado pela aplicação:
    ```sql
    CREATE DATABASE pdv_db;
    ```

3.  **Configure a Conexão:**
    Abra o arquivo `src/main/resources/application.properties` e ajuste as seguintes propriedades com as suas credenciais do PostgreSQL, se forem diferentes do padrão:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/pdv_db
    spring.datasource.username=postgres
    spring.datasource.password=sua-senha-aqui
    ```

## Como Executar a Aplicação

1.  **Build do Projeto (via Maven):**
    No terminal, na raiz do projeto, execute o comando para baixar as dependências e compilar o projeto:
    ```bash
    mvn clean install
    ```

2.  **Execute a Aplicação:**
    Você pode iniciar a aplicação diretamente pela sua IDE, executando a classe principal `PdvpostocombustivelApplication.java`, ou via linha de comando com o Maven:
    ```bash
    mvn spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API (Swagger)

Com a aplicação em execução, a documentação interativa da API, gerada pelo Swagger UI, pode ser acessada no seguinte endereço:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Nesta página, você pode visualizar todos os endpoints disponíveis, seus parâmetros, e testá-los diretamente.
